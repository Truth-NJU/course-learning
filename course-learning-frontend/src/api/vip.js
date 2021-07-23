// eslint-disable-next-line no-unused-vars
import axios from "axios";
// eslint-disable-next-line no-unused-vars
import { RECHARGE_MODULE, VIP_MODULE } from "@/api/_prefix";

export const get_vip_info = info => {
  console.log(info);
  /*return axios.get(`${VIP_MODULE}/price`).then(res => {
    return res.data;
  });*/
  return Promise.resolve({
    code: 2001,
    msg: "ok",
    data: [
      {
        id: 0,
        money: 100,
        time: 7,
        type: "week"
      },
      {
        id: 1,
        money: 400,
        time: 30,
        type: "month"
      },
      {
        id: 2,
        money: 1000,
        time: 90,
        type: "season"
      },
      {
        id: 3,
        money: 3600,
        time: 365,
        type: "year"
      }
    ]
  });
};

export const buy_vip = result =>{
  console.log(result)
  const { userId, type } = result;
  return axios.post(`${VIP_MODULE}/get`, { userId, type }).then(res => {
    return res.data;
  });
}
/*
export const buy_vip = result => {
  console.log(result);
  return Promise.resolve({
    code: 6004,
    msg: "钱不够"
  });
};*/
