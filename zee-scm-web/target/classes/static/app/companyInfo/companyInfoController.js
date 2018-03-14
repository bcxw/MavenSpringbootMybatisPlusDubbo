Ext.define('app.companyInfo.companyInfoController', {
    extend: 'Ext.app.ViewController',
    alias : 'controller.companyInfoController',
    /* 添加公司基本信息窗口 */
    showAddWindow:function(){
        Ext.create("app.companyInfo.companyInfoEditWindow",{title:'添加',width:this.getView().getWidth(),height:this.getView().getHeight(),x:this.getView().getX(),y:this.getView().getY()});
    },

    /*  添加公司基本信息 */
    submitCompanyInfo:function(button){
        var formPanel=button.up("companyInfoEditWindow").down("form");
        if(formPanel.getForm().isValid()){
         var formValues=formPanel.getForm().getValues();
            formPanel.getForm().submit({
                url:'companybaseinfo/save',
                params:{
                    companyCode: formValues['companyCode'],
                    companyMark: formValues['companyMark'],
                    companyName: formValues['companyName']
                },
                method :'post',
                success: function(form, action) {
                    if (action.result.success) {
                        Ext.Msg.alert('系统提示', '保存成功！');
                        var companyInfoGrid=app.getApplication().getMainView().down("companyInfoGridPanel");
                        var selectedNode=basedataTreeGrid.getSelection();
                        basedataTreeGrid.store.load({node:selectedNode});
                        selectedNode.expand();
                        button.up("companyInfoEditWindow").close();
                    }else{
                        Ext.Msg.alert('系统提示', '保存失败！');
                    }
                },
                failure: function(form, action) {
                    Ext.Msg.alert('操作失败',action&&action.result?action.result.message:"访问服务器失败，请联系管理员！");

                }

            });
        }else{
            Ext.Msg.alert('系统提示',"请填写公司编码、公司标记和公司名称");
        }
    },

    /*  按条件查询 */
    search:function(button){

        button.

    },
    /*  查看 */
    showSeeWindow:function(){
    },


    /* 添加界面公司属性显示隐藏*/
    onAllZtChecked:function(checkgroup,inputValue) {
        if (inputValue.gszt == 1) {
            checkgroup.up("companyInfoEditWindow").down("basedataCheckboxGroup[name=cgzt]").hide();
            checkgroup.up("companyInfoEditWindow").down("basedataCheckboxGroup[name=yuyingzt]").show();
        } else if (inputValue.gszt == 2) {
            checkgroup.up("companyInfoEditWindow").down("basedataCheckboxGroup[name=yuyingzt]").hide();
            checkgroup.up("companyInfoEditWindow").down("basedataCheckboxGroup[name=cgzt]").show();
        } else if (inputValue.gszt && inputValue.gszt.length == 2) {
            checkgroup.up("companyInfoEditWindow").down("basedataCheckboxGroup[name=yuyingzt]").show();
            checkgroup.up("companyInfoEditWindow").down("basedataCheckboxGroup[name=cgzt]").show();
        } else {
            checkgroup.up("companyInfoEditWindow").down("basedataCheckboxGroup[name=yuyingzt]").hide();
            checkgroup.up("companyInfoEditWindow").down("basedataCheckboxGroup[name=cgzt]").hide();
        }
    },


    /* 删除表格数据*/
    deleteGrideData:function(button){
      var selectedNode= button.up("companyInfoGridPanel").getSelection();
      if(selectedNode&&selectedNode.length>0){
            var selectedNode=selectedNode[0];
          Ext.Msg.confirm("系统提示","您确定要删除此项数据吗？删除后将不可恢复！",function(button,value,opt){
                if(button="yes"){
                    Ext.Ajax.request({
                        url: 'companybaseinfo/remove',
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
          Ext.Msg.alert('系统提示','请选择要删除的数据！');
      }
    }
});