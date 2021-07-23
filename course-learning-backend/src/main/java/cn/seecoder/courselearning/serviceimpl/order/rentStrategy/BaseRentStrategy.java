package cn.seecoder.courselearning.serviceimpl.order.rentStrategy;

public class BaseRentStrategy implements RentStrategy {

    @Override
    public int calRentMoney(int origin, int time) {
        return (int) (time * 1.0 * (180 - time) / 180 * origin / 45);
    }
}
