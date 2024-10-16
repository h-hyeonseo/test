import java.util.ArrayList;
import java.util.Arrays;

public class Pizza {
    private String name;      // 피자 이름
    private String size;      // 피자 크기 (small, medium, large)
    private int basePrice;    // 기본 가격
    private int toppingCount; // 추가된 토핑의 수
    private int toppingPrice; // 토핑 하나당 가격
    private ArrayList<String> toppings; // 추가된 토핑 리스트

    // 새로 수정된 생성자
    public Pizza(String pizzaType, String selectedSize, String[] array) {
        this.name = pizzaType;  // 피자 이름 설정
        this.size = selectedSize;  // 피자 크기 설정
        this.basePrice = calculateBasePrice(selectedSize);  // 크기에 따른 기본 가격 설정
        this.toppings = new ArrayList<>(Arrays.asList(array));  // 토핑 리스트 초기화
        this.toppingCount = array.length;  // 토핑 개수 설정
        this.toppingPrice = 1000;  // 토핑당 가격 설정
    }

    // 피자 기본 가격 계산 메서드
    private int calculateBasePrice(String size) {
        switch(size.toLowerCase()) {
            case "small": return 8000;
            case "medium": return 10000;
            case "large": return 12000;
            default: return 0;
        }
    }

    // 피자 가격 계산 (기본 가격 + 토핑 가격)
    public int calculatePrice() {
        return basePrice + (toppingCount * toppingPrice);
    }

    // 피자 정보를 문자열로 반환하는 메서드
    @Override
    public String toString() {
        return "피자: " + name +"\n"+ "사이즈: " + size + "\n가격: " + calculatePrice() + "원 \n토핑: " + toppings.toString() +"(추가금 "+ (toppingPrice * toppingCount)+"원)";
    }
}
