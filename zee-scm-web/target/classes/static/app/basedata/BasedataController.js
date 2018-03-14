Ext.define('app.basedata.BasedataController', {
    extend: 'Ext.app.ViewController',
    alias : 'controller.basedataController',
    extendJsonDelete:function(button){
        var grid=button.up("grid");
        var recs=grid.getSelection();
        if(recs&&recs.length>0){
            grid.store.remove(recs[0]);
        }else{
            Ext.Msg.alert('系统提示', '请选择要删除的数据！');
        }

    },
    extendJsonAdd:function(button){
        var grid=button.up("grid");
        var rec=grid.store.insert(0, {});
        grid.findPlugin('cellediting').startEditByPosition({row: 0, column: 0});
    },
    deleteBaseData:function(button){
        var selectedNode=button.up("basedataTreeGrid").getSelection();
        if(selectedNode&&selectedNode.length>0){
            selectedNode=selectedNode[0];
            Ext.Msg.confirm("系统提示","您确定要删除此项数据吗？删除后将不可恢复！",function(button,value,opt){
                if(button=="yes"){
                    Ext.Ajax.request({
                        url: 'basedata/removeById',
                        params:{id:selectedNode.data.id},
                        success: function(response, opts) {
                            var obj = Ext.decode(response.responseText);
                            if(obj.success){
                                Ext.Msg.alert('系统提示',"删除成功！");
                                var basedataTreeGrid=app.getApplication().getMainView().down("basedataTreeGrid");
                                basedataTreeGrid.store.load({node:selectedNode.parentNode});
                                selectedNode.parentNode.expand();
                            }else{
                                Ext.Msg.alert('删除失败',obj.message);
                            }
                        },
                        failure: function(response, opts) {
                            Ext.Msg.alert('操作失败',response.status+response.responseText);
                        }
                    });
                }
            });

        }else{
            Ext.Msg.alert('系统提示','请选择要删除的数据！')
        }
    },
    showAddWindow:function(button){
        var window=Ext.create("app.basedata.BasedataEditWindow",{title:'添加',width:this.getView().getWidth(),height:this.getView().getHeight(),x:this.getView().getX(),y:this.getView().getY()});
        window.down("grid[name='extendJsonGrid']").store.removeAll();
        var selectedNode=button.up("basedataTreeGrid").getSelection();
        if(selectedNode&&selectedNode.length>0){
            selectedNode=selectedNode[0];
            window.down("textfield[name='code']").setValue(selectedNode.data.code+"-");
        }else{
            selectedNode={data:{name:'顶级分类',id:0}};
        }
        window.down("hidden[name='parentId']").setValue(selectedNode.data.id);
        window.down("textfield[name='parentName']").setValue(selectedNode.data.name);


    },
    showEditWindow:function(button){
        var selectedNode=button.up("basedataTreeGrid").getSelection();
        if(selectedNode&&selectedNode.length>0){
            selectedNode=selectedNode[0];
            var window=Ext.create("app.basedata.BasedataEditWindow",{title:'添加',width:this.getView().getWidth(),height:this.getView().getHeight(),x:this.getView().getX(),y:this.getView().getY()});
            var extendJsonGrid = window.down("grid[name='extendJsonGrid']");
            extendJsonGrid.store.removeAll();
            var formPanel=window.down("form");
            formPanel.getForm().loadRecord(selectedNode);
            if(selectedNode.data.extendJson&&selectedNode.data.extendJson.indexOf('{')!=-1){
                extendJsonGrid.store.loadData(Ext.decode(selectedNode.data.extendJson));
            }

            formPanel.down("hidden[name='parentId']").setValue(selectedNode.parentNode.data.id);
            formPanel.down("textfield[name='parentName']").setValue(selectedNode.parentNode.data.name);
        }else{
            Ext.Msg.alert('系统提示','请选择要修改的数据！')
        }
    },
    submitBasedata:function(button){
        var formPanel=button.up("basedataEditWindow").down("form");
        if(formPanel.getForm().isValid()){

            var extendJsonGrid=formPanel.down("grid[name='extendJsonGrid']");
            var extendJsonRows=extendJsonGrid.store.getData().items;

            var extendJson="";
            if(extendJsonRows&&extendJsonRows.length>0){
                var extendJsonArr=new Array();
                Ext.Array.each(extendJsonRows, function(row, index, countriesItSelf) {
                    if(row&&row.data&&row.data.name){
                        extendJsonArr.push({name:row.data.name,code:row.data.code,value:row.data.value,description:row.data.description,sortorder:row.data.sortorder});
                    }

                });
                extendJson=Ext.encode(extendJsonArr);
            }

            formPanel.getForm().submit({
                url: 'basedata/save',
                params: {
                    extendJson:extendJson
                },
                success: function(form, action) {
                    if(action.result.success){
                        Ext.Msg.alert('系统提示', '保存成功！');

                        var basedataTreeGrid=app.getApplication().getMainView().down("basedataTreeGrid");
                        var selectedNode=basedataTreeGrid.getSelection();

                        if(formPanel.down("hidden[name='id']").getValue()){
                            selectedNode=selectedNode[0];
                            basedataTreeGrid.store.load({node:selectedNode.parentNode});
                            selectedNode.parentNode.expand();
                        }else{
                            if(selectedNode&&selectedNode.length>0){
                                selectedNode=selectedNode[0];
                            }else{
                                selectedNode=basedataTreeGrid.getRootNode();
                            }
                            basedataTreeGrid.store.load({node:selectedNode});
                            selectedNode.expand();
                        }

                        button.up("basedataEditWindow").close();

                    }else{
                        Ext.Msg.alert('系统提示',action.result.message);
                    }

                },
                failure: function(form, action) {
                    Ext.Msg.alert('操作失败',action&&action.result?action.result.message:"访问服务器失败，请联系管理员！");

                }
            });
        }else{
            Ext.Msg.alert('系统提示',"请填写名称和编码");
        }

    }

});