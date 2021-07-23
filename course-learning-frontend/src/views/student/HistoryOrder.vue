<template>
  <div>
    <v-container class="ma-8 pa-4">
      <v-card
          v-for="o in orderList"
          :key="o.id"
          :color="colorList[o.id % colorList.length]"
          class="ma-8 pa-4"
      >
        <v-card-title class="headline">
          {{ o.courseName }}
        </v-card-title>

        <v-card-subtitle class="mt-1">
          创建时间: {{ o.createTime }}
        </v-card-subtitle>
        <v-card-subtitle v-if="orderIsRent(o)" class="mt-1">
          租用时间: {{ o.time }}
        </v-card-subtitle>
        <v-card-actions class="pa-0 pl-2">
          <v-btn v-if="orderIsOk(o)" text @click="handleStudy(o.courseId)">学习课程</v-btn>
          <v-btn v-if="orderToRent(o)" text @click="handleRent(o)">支付订单</v-btn>
          <v-btn v-if="orderToBuy(o)" text @click="handleBuy(o)">支付订单</v-btn>
        </v-card-actions>
      </v-card>

      <v-dialog v-model="dialogRentPay" width="1000">
        <v-card>
          <v-card-title>支付订单</v-card-title>
          <v-card-text>租用时间:  {{ currentOrderRentTime }}</v-card-text>
          <v-card-text>租用费用:  {{ currentOrderCost }}</v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="pay()">支付</v-btn>
            <v-btn color="primary" text @click="dialogRentPay=false">取消</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="dialogBuyPay" width="1000">
        <v-card>
          <v-card-title>支付订单</v-card-title>
          <v-card-text>购买费用:{{ currentOrderCost }}</v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" text @click="pay()">支付</v-btn>
            <v-btn color="primary" text @click="dialogBuyPay=false">取消</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-snackbar v-model="showSnackBar" :color="snackBarColor" top>
        {{ snackBarMsg }}
      </v-snackbar>
    </v-container>
  </div>
</template>

<script>
import {getOrdersByUser, payOrder} from "@/api/order";

export default {
  name: "HistoryOrder",
  data() {
    return {
      colorList: [
        "#FFAB91",
        "#26A69A",
        "#039BE5",
        "#546E7A",
        "#B39DDB",
        "#EF9A9A"
      ],
      orderList: [],
      dialogBuyPay: false,
      dialogRentPay: false,
      currentOrderId: "",
      currentOrderCost: "",
      currentOrderRentTime: "",
      showSnackBar:"",
      snackBarColor:"",
      snackBarMsg:""
    };
  },

  methods: {
    handleStudy(courseId) {
      this.$router.push(`/student/course/${courseId}`);
    },
    handleRent(order) {
      this.dialogRentPay = true
      this.currentOrderCost = order.cost
      this.currentOrderId = order.id
      this.currentOrderRentTime = order.time
    },
    handleBuy(order) {
      this.dialogBuyPay = true
      this.currentOrderCost = order.cost
      this.currentOrderId = order.id
    },
    pay() {
      payOrder(this.currentOrderId).then(res => {
        if (res.code === 0) {
          this.snackBarColor = "error";
          this.snackBarMsg = res.msg;
          this.showSnackBar = true;
          this.dialog = false;
        }
      })
      this.dialogRentPay = false
      this.dialogBuyPay = false
    },
    orderToRent(order) {
      return this.orderIsRent(order) && !this.orderIsOk(order);
    },
    orderToBuy(order) {
      return order.purchaseType === 'Buy' && order.status !== 1
    },
    orderIsRent(order) {
      return order.purchaseType === 'Rent'
    },
    orderIsOk(order) {
      if (order.purchaseType === "Buy" && order.status === 1)
        return true
      if (order.purchaseType === 'Rent'&& order.status === 1) {
        let now = Date.parse(Date());
        let orderEndTime = Date.parse(order.endTime)
        if (orderEndTime > now) {
          return true
        }
      }
      return false
    }
  },

  mounted() {
    const uid = window.localStorage.getItem("userId");
    getOrdersByUser(uid).then(res => {
      console.log(res);
      this.orderList = res || [];
    });
  }
};
</script>
