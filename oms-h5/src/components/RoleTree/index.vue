<template>
  <div class="role-tree-container">
    <ul>
      <li class="parent-li">
        <span class="item"><i class="el-icon-fa-bars"></i> 根目录</span>
        <ul>
          <li class="parent-li" v-for="(node, nodeIndex) in trees" v-if="node.childList.length != 0">
            <span class="item">
              <el-checkbox v-model="node.checked" @change="nodeChange(nodeIndex, node.checked)">{{node.name}}</el-checkbox>
            </span>
            <ul v-if="node.display && node.childList">
              <li v-for="(menu, menuIndex) in node.childList">
                <span class="item">
                  <el-checkbox v-model="menu.checked" @change="menuChange(nodeIndex, menuIndex, menu.checked)">{{menu.name}}</el-checkbox>
                  <hr v-if="menu.permissionList.length != 0">
                  <el-checkbox v-for="(permission, permissionIndex) in menu.permissionList" v-model="permission.checked" @change="permissionChange(nodeIndex, menuIndex, permissionIndex, permission.checked)">{{permission.name}}</el-checkbox>
                </span>
              </li>
            </ul>
          </li>
        </ul>
      </li>
    </ul>
  </div>
</template>

<script>
import { findMenus } from '@/api/sys/role'

export default {
  props: {
    roleId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      trees: []
    }
  },
  created() {
    // 查询菜单权限
    findMenus(this.roleId).then(response => {
      this.parseMenu(response.menus)
    })
  },
  methods: {
    /**
     * 解析菜单
     */
    parseMenu(menus) {
      menus.forEach((item, index) => {
        // 一级节点
        const node = {
          id: item.id,
          name: item.name,
          status: item.status,
          checked: item.checked,
          display: true,
          childList: []
        }
        item.childList.forEach((second, index) => {
          // 二级菜单
          const menu = {
            id: second.id,
            name: second.name,
            status: second.status,
            checked: second.checked,
            permissionList: []
          }
          second.permissionList.forEach((item, index) => {
            // 三级权限点
            const permission = {
              id: item.id,
              name: item.name,
              checked: item.checked
            }
            menu.permissionList.push(permission)
          })
          node.childList.push(menu)
        })
        this.trees.push(node)
      })
    },

    /**
     * 切换显示状态
     */
    toggleDisplay(node) {
      node.display = !node.display
    },

    /**
     * 获取选中的权限点
     */
    getCheckedIds() {
      const menuIds = []
      const permissionIds = []
      this.trees.forEach(node => {
        node.checked && menuIds.push(node.id)
        node.childList.forEach(menu => {
          menu.checked && menuIds.push(menu.id)
          menu.permissionList.forEach(permission => {
            permission.checked && permissionIds.push(permission.id)
          })
        })
      })
      return {
        'menuId[]': menuIds,
        'permissionId[]': permissionIds
      }
    },

    /**
     * 更新节点状态
     */
    nodeChange(nodeIndex, checked) {
      this.trees[nodeIndex].childList.forEach(menu => {
        menu.checked = checked
        menu.permissionList.forEach(permission => {
          permission.checked = checked
        })
      })
    },

    /**
     * 更新菜单状态
     */
    menuChange(firstIndex, secondIndex, checked) {
      // 更新二级菜单下的权限点
      this.trees[firstIndex].childList[secondIndex].permissionList.forEach(item => {
        item.checked = checked
      })

      if (checked) {
        this.trees[firstIndex].checked = true
      } else {
        let menuChecked = false
        this.trees[firstIndex].childList.forEach(menu => {
          menuChecked = menuChecked | menu.checked
        })
        this.trees[firstIndex].checked = menuChecked === 1
      }
    },

    /**
     * 更显权限点状态
     */
    permissionChange(firstIndex, secondIndex, thirdIndex, checked) {
      if (checked) {
        // 若为选中，则更新一级菜单和二级菜单的选中状态也为选中
        this.trees[firstIndex].checked = true
        this.trees[firstIndex].childList[secondIndex].checked = true
      } else {
        // 判断是否有一个兄弟权限点选中，若存在一个及以上，则设置二级菜单为选中状态
        let permissionChecked = false
        this.trees[firstIndex].childList[secondIndex].permissionList.forEach((item) => {
          permissionChecked = permissionChecked | item.checked
        })
        this.trees[firstIndex].childList[secondIndex].checked = permissionChecked === 1

        // 判断是否有一个二级菜单被选中，若存在一个及以上，则设置一级菜单为选中状态
        let nodeChecked = false
        this.trees[firstIndex].childList.forEach(item => {
          nodeChecked = nodeChecked | item.checked
        })
        this.trees[firstIndex].checked = nodeChecked === 1
      }
    }
  }
}
</script>

<style>

.role-tree-container ul {
  margin: 0;
}
.role-tree-container li {
  list-style-type: none;
  color: #1f2d3d;
  position: relative;
  padding: 10px 5px 0 5px;
}
.role-tree-container>ul>li:before,
.role-tree-container>ul>li:after {
  border: 0;
}
.role-tree-container li::before {
  border-left: 1px solid #bfcbd9;
  bottom: 50x;
  height: 100%;
  top: 0;
  width: 1px;
}
.role-tree-container li::after {
  border-top: 1px solid #bfcbd9;
  height: 20px;
  top: 25px;
  width: 25px;
}
.role-tree-container ul li:last-child::before {
  height: 26px;
}
.role-tree-container li::before, .role-tree-container li::after {
  content: '';
  left: -20px;
  position: absolute;
  right: auto;
}
.role-tree-container span.item {
  border: 1px solid #bfcbd9;
  border-radius: 4px;
  display: inline-block;
  padding: 0 14px;
  text-decoration: none;
}
.role-tree-container .parent-li>span {
  cursor: pointer;
}
.role-tree-container hr {
  border: 0;
  border-top: 1px solid #e4eaec;
  margin: 0;
}

</style>

