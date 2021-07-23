<template>

  <v-container class="float-left">
    <form class="pa-12 purple lighten-5 rounded-sm fixed">
      <v-text-field v-model="userInfo.id" label="ID" readonly></v-text-field>
      <v-text-field
          v-model="userInfo.uname"
          label="用户名"
          readonly
      ></v-text-field>
      <v-text-field
          v-model="userInfo.phone"
          label="手机号"
          readonly
      ></v-text-field>
      <v-text-field
          v-model="userInfo.userRole"
          label="用户角色"
          readonly
      ></v-text-field>
      <v-text-field
          v-model="userInfo.createTime"
          label="注册时间"
          readonly
      ></v-text-field>
      <v-text-field
          v-model="userInfo.balance"
          label="账户余额"
          readonly
      ></v-text-field>
      <v-btn color="primary" dark @click.stop="dialog = true">
        充值
      </v-btn>
    </form>
  </v-container>
</template>

<script>
export default {
  name: "try"
};
</script>

<style scoped></style>
