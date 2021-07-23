<template>
  <v-card class="pa-5" width="250" :color="color">
    <v-card-title>{{ type }}</v-card-title>
    <v-card-text>{{ money }}元</v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn @click="buy">
        购买
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "VipCard",

  props: {
    type: {
      type: String,
      default: "买个屁"
    },
    money: {
      type: Number,
      default: 999
    },
    color: {
      type: String,
      default: "#BCAAA4"
    },
    time: {
      type: Number,
      default: 0
    },
    id: {
      type: Number,
      default: 0
    }
  },

  methods: {
    buy() {
      this.$emit("buy-vip", this.id, this.type);
    }
  }
};
</script>

<style scoped></style>
