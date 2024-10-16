import java.util.ArrayList;

public class Order {
    private ArrayList<Pizza> cart;
    private ArrayList<String> sides;
    private ArrayList<Integer> sidePrices;
    private ArrayList<String> drinks;
    private ArrayList<Integer> drinkPrices;  // 음료 가격 리스트 추가

    public Order() {
        this.cart = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.sidePrices = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.drinkPrices = new ArrayList<>();
    }

    public void addPizzaToCart(Pizza pizza) {

        cart.add(pizza);
    }

    public void addSideToCart(String side, int price) {
        sides.add(side);
        sidePrices.add(price);  // 사이드 메뉴 가격 추가
    }

    public void addDrinkToCart(String drinkName, int price) {
        drinks.add(drinkName + " (" + price + "원)");
        drinkPrices.add(price);
    }


    public String displayCart() {
        StringBuilder cartContent = new StringBuilder();

        // 피자 목록 출력
        cartContent.append("~~피자 메뉴~~\n");
        for (Pizza pizza : cart) {
            cartContent.append(pizza.toString()).append("\n");
        }

        // 사이드메뉴 목록 출력
        cartContent.append("\n~~사이드 메뉴~~\n");
        if (sides.isEmpty()) {
            cartContent.append("선택된 사이드 메뉴가 없습니다.\n");
        } else {
            for (int i = 0; i < sides.size(); i++) {
                cartContent.append(sides.get(i)).append(" (").append(sidePrices.get(i)).append("원)").append("\n");  // 이름과 가격을 함께 출력
            }
        }

        // 음료 목록 출력
        cartContent.append("\n~~음료~~\n");
        if (drinks.isEmpty()) {
            cartContent.append("선택된 음료가 없습니다.\n");
        } else {
            for (String drink : drinks) {
                cartContent.append(drink).append("\n");
            }
        }

        cartContent.append("\n~~총 금액~~\n");
        cartContent.append("총 금액: " + calculateTotalPrice() + "원");



        return cartContent.toString();  // 최종적으로 문자열 반환
    }


    public int calculateTotalPrice() {
        int totalPrice = 0;
        for (Pizza pizza : cart) {
            totalPrice += pizza.calculatePrice();
        }
        for (int price : sidePrices) {
            totalPrice += price;   // 사이드 메뉴 가격 합산
        }
        for (int price : drinkPrices) {
            totalPrice += price;  // 음료 가격 합산
        }
        return totalPrice;
    }

    public void clearCart() {
        cart.clear();
        sides.clear();
        drinks.clear();
        System.out.println("장바구니가 비워졌습니다.");
    }

    public void displayOrder() {
        displayCart();
        System.out.println("총 금액: " + calculateTotalPrice() + "원");
    }
}
