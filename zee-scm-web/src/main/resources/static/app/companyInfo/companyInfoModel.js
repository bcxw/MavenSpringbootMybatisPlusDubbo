Ext.define('app.companyInfo.companyInfoModel',{
    extend:'app.view.ViewModel',
    alias:'viewmodel.companyInfoModel',

    stores:{
        companyInfoGridPanelStore:{
            autoLoad:true,
            type:"store",
            sorters:'sortorder',
            defaultRootProperty:"data",

            fields: [{
                name:'companyCode'
            },{
                name:'companyMark'
            },{
                name:'companyName'
            },{
                name:'companyProperty'
            },{
                name:'companyAddress'
            },{
                name:'remark'
            }],
            proxy: {
                type:'ajax',
                url:'companybaseinfo/list',
                reader: {
                    type: 'json',
                    rootProperty: 'data',
                    implicitIncludes: false
                }
            }
        }
    }
});