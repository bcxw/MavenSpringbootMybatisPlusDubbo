Ext.define('app.companyInfo.companyInfoGridPanel', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.companyInfoGridPanel',

    requires:["app.companyInfo.companyInfoModel","app.companyInfo.companyInfoController"],

    viewModel:"companyInfoModel",
    controller:'companyInfoController',

    closable:true,

    bind:{store:'{companyInfoGridPanelStore}'},
    selType: 'checkboxmodel',
    columnLines: true,
    tbar:{
        xtype:"form",
        items:[{
            xtype:"buttongroup",
            frame:false,
            items:[{
                xtype:"textfield",
                fieldLabel:"公司编码",
                labelWidth:60,
                width:180,
                labelAlign:"right"
            },{
                xtype:"textfield",
                fieldLabel:"公司名称",
                labelWidth:60,
                width:180,
                labelAlign:"right"
            },{
                xtype:"textfield",
                fieldLabel:"公司属性",
                labelWidth:60,
                width:180,
                labelAlign:"right"
            },{
                text:"查询",
                handler: 'search',
            },{
                text:"重置"
            }]
        },{
            xtype:"buttongroup",
            frame:false,
            items:[{
                text: '添加记录',
                handler: 'showAddWindow',
                glyph:0xf067
            },{
                text: '查看',
                handler: 'showSeeWindow',
                glyph:0xf064
            },{
                text: '修改',
                handler: 'showEditWindow',
                glyph:0xf040
            },{
                text: '删除',
                handler: 'deleteGrideData',
                glyph:0xf068
            }]
        }]
    },
    bbar:{
        xtype: 'pagingtoolbar',
        displayInfo: true
    },
    columns: [{
        xtype: 'rownumberer',
       // name:'companyInfoGrid'
    },{
        text: '公司编码',
        width: 85,
        align:'center',
        dataIndex: 'companyCode'
    },{
        text: '公司标记',
        width: 120,
        align:'center',
        dataIndex: 'companyMark'
    },{
        text: '公司名称',
        width: 300,
        align:'center',
        dataIndex: 'companyName'
    },{
        text: '公司属性',
        width: 120,
        align:'center',
        dataIndex: 'companyProperty'
    },{
        text: '公司地址',
        width: 400,
        align:'center',
        dataIndex: 'companyAddress'
    },{
        text: '备注',
        width: 400,
        align:'center',
        dataIndex: 'remark'
    }]
});