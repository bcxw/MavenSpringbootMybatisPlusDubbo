Ext.define('app.productLayout.layoutPlan.LayoutPlanModel',{
    extend:'app.view.ViewModel',
    alias:'viewmodel.layoutPlanModel',
    stores:{
        LayoutPlanGridPanelStore:{
            type:"store",
            fields: [{
                name: 'firstName'
            },{
                name: 'lastName'
            },{
                name: 'name'
            },{
                name: 'eyeColor'
            }],
            data:[{
                name:'1111111111111'
            },{
                name:'hhhhhhhhhhhhhhhhhhhhhhh'
            }]
        }
    }
});