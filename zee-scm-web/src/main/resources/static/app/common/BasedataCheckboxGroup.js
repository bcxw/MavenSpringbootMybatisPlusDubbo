/**
 * 基础数据主界面通用组件 复选组件
 * 示例：｛xtype:"basedataCheckboxGroup",basedataCode:"品牌",name:''｝
 */
Ext.define('app.common.BasedataCheckboxGroup', {
    extend: 'Ext.form.CheckboxGroup',
    alias: 'widget.basedataCheckboxGroup',

    basedataCode:'',
    name:'',

    initComponent:function(){
        var parentCode=this.basedataCode;
        var checkBoxGroupName=this.name;
        var me=this;
        Ext.Ajax.request({
            url: 'basedata/getBaseDataByParentCode',
            params:{parentCode:parentCode},
            async:false,
            success: function(response, opts) {
                var obj = Ext.decode(response.responseText);
                if(obj.success){
                    var basedataArr=obj.data;

                    if(basedataArr&&basedataArr.length>0){
                        var checkBoxItems=new Array();

                        Ext.Array.each(basedataArr, function(item, index, countriesItSelf) {
                            if (item.code&&item.name) {
                                checkBoxItems.push({boxLabel:item.name, name:checkBoxGroupName, inputValue:item.code});
                            }
                        });
                    }

                    me.items=checkBoxItems;

                }else{
                    Ext.Msg.alert('系统提示',obj.message);
                }
            },
            failure: function(response, opts) {
                Ext.Msg.alert('系统提示',response.status+response.responseText);
            }
        });

        this.callParent();
    }

});