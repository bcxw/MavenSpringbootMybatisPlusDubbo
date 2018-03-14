/**
 * 基础数据主界面通用组件 下拉框
 * 示例：｛xtype:"basedataCheckboxGroup",basedataCode:"品牌"｝
 */
Ext.define('app.common.BasedataCombobox', {
    extend: 'Ext.form.field.ComboBox',
    alias: 'widget.basedataCombobox',

    basedataCode:'',

    displayField: 'name',
    valueField: 'code',
    initComponent:function(){
        var parentCode=this.basedataCode;

        this.store={
            autoLoad: true,
            fields: [{
                name:'id'
            },{
                name:'name'
            },{
                name:'code'
            }],
            proxy: {
                type:'ajax',
                reader: {rootProperty:'data'},
                url:'basedata/getBaseDataByParentCode?parentCode='+parentCode
            }
        };

        this.callParent();
    }

});