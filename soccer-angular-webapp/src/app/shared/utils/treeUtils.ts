import {NzTreeComponent} from "ng-zorro-antd/tree";
import {NzTreeNode} from "ng-zorro-antd/core/tree/nz-tree-base-node";

/**
 * 获取选中的树节点，包括完全选中节点（及其下级节点）、半选中节点，
 *
 */
export function getCheckedNodes(treeComponent: NzTreeComponent): NzTreeNode[] {
  const halfCheckedNodeList = treeComponent.getHalfCheckedNodeList();
  const checkedNodeList = treeComponent.getCheckedNodeList();
  const checkedNodeListWithChildren = getAllWithChildren(checkedNodeList);
  return [...halfCheckedNodeList, ...checkedNodeListWithChildren];
}


function getAllWithChildren(nodeList: NzTreeNode[]): NzTreeNode[] {
  let list: NzTreeNode[] = [];
  nodeList.forEach(node => {
    list.push(node);
    if (node.children && node.children.length > 0) {
      const childrenList = getAllWithChildren(node.children);
      childrenList.forEach(child => list.push(child));
    }
  });
  return list;

}
