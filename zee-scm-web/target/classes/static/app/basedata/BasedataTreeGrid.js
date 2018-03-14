/**
 * 基础数据主界面
 */
Ext.define('app.basedata.BasedataTreeGrid', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.basedataTreeGrid',

    requires:["app.basedata.BasedataController",'app.basedata.BasedataModel'],

    viewModel:"basedataModel",
    controller:'basedataController',

    closable:true,
    allowDeselect:true,

    bind:{store:'{basedataTreeGridStore}'},
    rootVisible: false,
    columnLines:true,
    tbar: [{
        text: '添加',
        handler: 'showAddWindow',
        glyph:0xf067,
        bind:{disabled:'{!addData}'}
    },{
        text: '修改',
        handler: 'showEditWindow',
        glyph:0xf040,
        bind:{disabled:'{!modifyData}'}
    },{
        text: '删除',
        handler: 'deleteBaseData',
        glyph:0xf068,
        bind:{disabled:'{!removeData}'}
    }],
    columns: [{
        xtype: 'treecolumn',
        text: '名称',
        dataIndex: 'name',
        width:300
    },{
        text: '编码',
        dataIndex: 'code',
        width:100
    },{
        text: '值',
        dataIndex: 'value',
        width:100
    },{
        text: '备注',
        dataIndex: 'description',
        width:150
    },{
        text: '排序',
        dataIndex: 'sortorder',
        width:80
    },{
        text: '扩展数据',
        dataIndex: 'extendJson',
        width:300
    }]
});