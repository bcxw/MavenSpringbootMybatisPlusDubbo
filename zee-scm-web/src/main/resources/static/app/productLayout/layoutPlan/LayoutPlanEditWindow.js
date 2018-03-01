Ext.define('app.productLayout.layoutPlan.LayoutPlanEditWindow',{
    extend: 'Ext.window.Window',
    alias: 'widget.layoutPlanEditWindow',

    autoShow:true,
    modal:true,
    layout:"fit",
    buttonAlign:'center',
    buttons: [{
        text: '保存'
    }],
    items:[{
        margin:10,
        xtype:"form",
        border:false,
        items:[{
            layout:'column',
            border:false,
            items:[{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '菜单名称',
                    width:'100%'
                }]
            },{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '上级菜单',
                    width:'100%'
                }]
            },{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '图标',
                    width:'100%'
                }]
            },{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '排序',
                    width:'100%'
                }]
            }]
        },{
            xtype:'textfield',
            labelAlign:'right',
            fieldLabel: '地址',
            width:'100%'
        }]
    }]
});