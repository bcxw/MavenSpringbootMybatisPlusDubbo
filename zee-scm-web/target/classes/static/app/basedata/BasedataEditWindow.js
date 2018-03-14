Ext.define('app.basedata.BasedataEditWindow',{
    extend: 'Ext.window.Window',
    alias: 'widget.basedataEditWindow',

    requires:["app.basedata.BasedataController","app.basedata.BasedataTreeGrid"],

    controller:'basedataController',

    autoShow:true,
    modal:true,
    layout:"fit",
    buttonAlign:'center',
    buttons: [{
        text: '保存',
        handler: 'submitBasedata'
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
                    xtype:"hidden",
                    name:"id"
                },{
                    xtype:"hidden",
                    name:"parentId"
                },{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '上级分类',
                    name:"parentName",
                    editable:false,
                    width:'100%'
                }]
            },{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '名称',
                    allowBlank:false,
                    name:'name',
                    width:'100%'
                }]
            },{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '编码',
                    allowBlank:false,
                    name:'code',
                    width:'100%'
                }]
            },{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '值',
                    name:'value',
                    width:'100%'
                }]
            }]
        },{
            layout:'column',
            border:false,
            items:[{
                columnWidth: 0.75,
                border:false,
                items:[{
                    xtype:'textfield',
                    labelAlign:'right',
                    fieldLabel: '备注',
                    name:'description',
                    width:'100%'
                }]
            },{
                columnWidth: 0.25,
                border:false,
                items:[{
                    xtype:'numberfield',
                    labelAlign:'right',
                    fieldLabel: '排序',
                    name:'sortorder',
                    width:'100%'
                }]
            }]
        },{
            xtype: 'fieldset',
            title: '扩展数据',
            height:300,
            layout:'fit',
            padding:"0 0 0 5",
            items:[{
                border:false,
                xtype:'grid',
                name:"extendJsonGrid",
                columnLines:true,
                plugins: {
                    ptype: 'cellediting',
                    clicksToEdit: 1
                },
                tbar:[{
                    text: '添加',
                    handler: 'extendJsonAdd',
                    glyph:0xf067
                },{
                    text: '删除',
                    handler: 'extendJsonDelete',
                    glyph:0xf068
                }],
                columns: [{
                    text: '名称',
                    dataIndex: 'name',
                    width: 150,
                    editor: {
                        allowBlank: false
                    }
                }, {
                    text: '编码',
                    width: 100,
                    dataIndex: 'code',
                    editor: {
                        allowBlank: false
                    }
                }, {
                    text: '值',
                    width: 100,
                    dataIndex: 'value',
                    editor: {
                    }
                }, {
                    text: '描述',
                    width: 200,
                    dataIndex: 'description',
                    editor: {
                    }
                }, {
                    text: '排序',
                    width: 80,
                    dataIndex: 'sortorder',
                    editor: {
                        xtype: 'numberfield'
                    }
                }]
            }]
        }]
    }]
});