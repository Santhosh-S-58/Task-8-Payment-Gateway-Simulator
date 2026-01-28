interface Payment {

    void pay(double amount);

    default void paymentStatus(boolean status) {
        if (status) {
            System.out.println("Payment Successful");
        } else {
            System.out.println("Payment Failed");
        }
    }
}

interface Refundable {
    void refund(double amount);
}

class CreditCardPayment implements Payment, Refundable {

    @Override
    public void pay(double amount) {
        if (amount > 0) {
            System.out.println("Processing Credit Card payment of " + amount);
            paymentStatus(true);
        } else {
            paymentStatus(false);
        }
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to Credit Card");
    }
}

class UpiPayment implements Payment {

    @Override
    public void pay(double amount) {
        if (amount > 0) {
            System.out.println("Processing UPI payment of " + amount);
            paymentStatus(true);
        } else {
            paymentStatus(false);
        }
    }
}

class PaymentProcessor {

    public void processPayment(Payment payment, double amount) {
        payment.pay(amount);
    }
}

public class PaymentGateway {

    public static void main(String[] args) {

        Payment creditCard = new CreditCardPayment();
        Payment upi = new UpiPayment();

        PaymentProcessor processor = new PaymentProcessor();

        System.out.println("---- Credit Card Payment ----");
        processor.processPayment(creditCard, 2500);

        System.out.println("\n---- UPI Payment ----");
        processor.processPayment(upi, 1500);

        System.out.println("\n---- Invalid Payment ----");
        processor.processPayment(upi, -500);

        Refundable refund = new CreditCardPayment();
        refund.refund(500);
    }
}
