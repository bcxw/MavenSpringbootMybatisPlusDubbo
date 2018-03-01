Ext.define('app.productLayout.layoutPlan.LayoutPlanGridPanel', {
    extend: 'Ext.grid.Panel',

    requires:["app.productLayout.layoutPlan.LayoutPlanModel","app.productLayout.layoutPlan.LayoutPlanController"],

    viewModel:"layoutPlanModel",
    controller:'layoutPlanController',

    closable:true,

    bind:{store:'{LayoutPlanGridPanelStore}'},
    selType: 'checkboxmodel',
    tbar: [{
        text: '添加',
        handler: 'showEditWindow',
        glyph:0xf067
    }],
    bbar:{
        xtype: 'pagingtoolbar',
        displayInfo: true
    },
    columns: [{
        xtype: 'rownumberer'
    },{
        text: '价格政策',
        dataIndex: 'name'
    },{
        text: '意见',
        dataIndex: 'name'
    },{
        text: '审核状态',
        dataIndex: 'name'
    },{
        text: '品牌',
        dataIndex: 'name'
    },{
        text: '年份',
        dataIndex: 'name'
    },{
        text: '季节',
        dataIndex: 'name'
    },{
        text: '月份',
        dataIndex: 'name'
    },{
        text: '预计上架日期',
        dataIndex: 'name'
    },{
        text: '上新波段',
        dataIndex: 'name'
    }]
});