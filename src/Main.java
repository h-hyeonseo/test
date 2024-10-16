import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {

    private Order order = new Order();  // Order 객체 생성
    private JTextArea cartDisplay;  // 장바구니 표시용

    public Main() {
        // 메인 창 설정
        setTitle("피자 주문 키오스크");
        setSize(800, 600);  // 창 크기 확대
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 245, 245));  // 배경색 추가

        // 인사말 패널
        JLabel greetingLabel = new JLabel("☆도미노피자에 오신 것을 환영합니다!☆", JLabel.CENTER);
        greetingLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));  // 더 큰 글꼴
        greetingLabel.setForeground(new Color(0, 123, 255));  // 텍스트 색상 추가

        // 메뉴 선택 패널
        JPanel menuPanel = new JPanel(new GridLayout(3, 2, 10, 10));  // 버튼 사이의 간격 확대
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // 패널 여백 추가

        JButton pizzaButton = createStyledButton("피자 선택");
        JButton sideButton = createStyledButton("사이드 메뉴 선택");
        JButton drinkButton = createStyledButton("음료 선택");
        JButton cartButton = createStyledButton("장바구니 확인");
        JButton orderButton = createStyledButton("결제하기");

        // 피자 선택 버튼에 이벤트 추가
        pizzaButton.addActionListener(e -> openPizzaSelection());

        // 사이드 메뉴 선택 버튼에 이벤트 추가
        sideButton.addActionListener(e -> openSideSelection());

        // 음료 선택 버튼에 이벤트 추가
        drinkButton.addActionListener(e -> openDrinkSelection());

        // 장바구니 확인 버튼에 이벤트 추가
        cartButton.addActionListener(e -> displayCart());

        // 결제하기 버튼에 이벤트 추가
        orderButton.addActionListener(e -> {
            if (order.calculateTotalPrice() > 0) {
                openPaymentWindow();
            } else {
                JOptionPane.showMessageDialog(null, "장바구니가 비어 있습니다. 결제할 항목이 없습니다.");
            }
        });

        // 패널에 버튼 추가
        menuPanel.add(pizzaButton);
        menuPanel.add(sideButton);
        menuPanel.add(drinkButton);
        menuPanel.add(cartButton);
        menuPanel.add(orderButton);


        // 메인 패널 구성
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));  // 메인 패널 여백 추가
        mainPanel.add(greetingLabel, BorderLayout.NORTH);  // 인사말 추가
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        //mainPanel.add(scrollPane, BorderLayout.SOUTH);

        // 메인 프레임에 패널 추가
        getContentPane().add(mainPanel);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 16));  // 버튼 폰트 설정
        button.setBackground(new Color(0, 123, 255));  // 버튼 배경색 설정
        button.setForeground(Color.WHITE);  // 버튼 텍스트 색상
        button.setFocusPainted(false);  // 포커스 페인트 제거
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));  // 버튼 테두리 설정


        return button;
    }

    // 피자 선택 화면 (JButton으로 변경)
    // 피자 선택 화면 (JButton으로 변경)
    private void openPizzaSelection() {
        JFrame pizzaFrame = new JFrame("피자 선택");
        pizzaFrame.setSize(500, 500);
        pizzaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pizzaFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JButton cheesePizzaButton = new JButton("치즈 피자 (8000원)");
        JButton pepperoniPizzaButton = new JButton("페퍼로니 피자 (10000원)");
        JButton hawaiianPizzaButton = new JButton("하와이안 피자 (12000원)");
        JButton bulgogiPizzaButton = new JButton("불고기 피자 (11000원)");
        JButton potatoPizzaButton = new JButton("포테이토 피자 (13000원)");

        ActionListener pizzaButtonListener = e -> {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();
            String selectedPizza = buttonText.substring(0, buttonText.indexOf(" ("));  // 피자 이름 추출

            // 피자 크기 및 토핑 선택 창 열기
            openPizzaOptions(selectedPizza);  // 정확한 피자 이름을 전달
            pizzaFrame.dispose(); // 기존 창 닫기
        };


        // 각 버튼에 이벤트 리스너 추가
        cheesePizzaButton.addActionListener(pizzaButtonListener);
        pepperoniPizzaButton.addActionListener(pizzaButtonListener);
        hawaiianPizzaButton.addActionListener(pizzaButtonListener);
        bulgogiPizzaButton.addActionListener(pizzaButtonListener);
        potatoPizzaButton.addActionListener(pizzaButtonListener);

        // 패널에 버튼 추가
        panel.add(cheesePizzaButton);
        panel.add(pepperoniPizzaButton);
        panel.add(hawaiianPizzaButton);
        panel.add(bulgogiPizzaButton);
        panel.add(potatoPizzaButton);

        pizzaFrame.getContentPane().add(panel);
        pizzaFrame.setVisible(true);
    }
    // 피자 크기 및 토핑 선택 창
    private void openPizzaOptions(String pizzaType) {
        JFrame optionFrame = new JFrame("피자 옵션 선택");
        optionFrame.setSize(500, 500);
        optionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));

        // 피자 크기 선택 라벨 및 라디오 버튼
        JLabel sizeLabel = new JLabel("피자 크기를 선택하세요:");
        JRadioButton smallButton = new JRadioButton("Small (+0원)");
        JRadioButton mediumButton = new JRadioButton("Medium (+2000원)", true);  // 기본 선택
        JRadioButton largeButton = new JRadioButton("Large (+4000원)");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallButton);
        sizeGroup.add(mediumButton);
        sizeGroup.add(largeButton);

        // 토핑 선택 체크박스
        JLabel toppingLabel = new JLabel("추가할 토핑을 선택하세요 (개당 +1000원):");
        JCheckBox cheeseTopping = new JCheckBox("치즈");
        JCheckBox pepperoniTopping = new JCheckBox("페퍼로니");
        JCheckBox mushroomTopping = new JCheckBox("버섯");
        JCheckBox oliveTopping = new JCheckBox("올리브");

        JButton addButton = new JButton("장바구니에 담기");

        // 장바구니에 추가 버튼 이벤트
        addButton.addActionListener(e -> {
            String selectedSize = "Medium"; // 기본 사이즈
            if (smallButton.isSelected()) selectedSize = "Small";
            if (mediumButton.isSelected()) selectedSize = "Medium";
            if (largeButton.isSelected()) selectedSize = "Large";

            // 선택된 토핑 리스트
            ArrayList<String> selectedToppings = new ArrayList<>();
            if (cheeseTopping.isSelected()) selectedToppings.add("치즈");
            if (pepperoniTopping.isSelected()) selectedToppings.add("페퍼로니");
            if (mushroomTopping.isSelected()) selectedToppings.add("버섯");
            if (oliveTopping.isSelected()) selectedToppings.add("올리브");

            // 옵션이 저장된 피자 객체 생성 및 장바구니에 추가
            Pizza pizza = new Pizza(pizzaType, selectedSize, selectedToppings.toArray(new String[0]));
            order.addPizzaToCart(pizza); //오더에 저장

            // 메시지 박스에 피자 이름과 함께 출력
            JOptionPane.showMessageDialog(null, pizzaType + "가 장바구니에 담겼습니다!");

            optionFrame.dispose(); // 옵션 창 닫기
        });


        // 패널에 컴포넌트 추가
        panel.add(sizeLabel);
        panel.add(smallButton);
        panel.add(mediumButton);
        panel.add(largeButton);
        panel.add(toppingLabel);
        panel.add(cheeseTopping);
        panel.add(pepperoniTopping);
        panel.add(mushroomTopping);
        panel.add(oliveTopping);
        panel.add(addButton);

        optionFrame.getContentPane().add(panel);
        optionFrame.setVisible(true);
    }


    // 사이드 메뉴 선택 화면
    private void openSideSelection() {
        JFrame sideFrame = new JFrame("사이드 메뉴 선택");
        sideFrame.setSize(500, 300);
        sideFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sideFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JCheckBox fries = new JCheckBox("감자튀김 (3000원)");
        JCheckBox cheeseStick = new JCheckBox("치즈스틱 (4000원)");
        JCheckBox wings = new JCheckBox("윙 (5000원)");

        JButton addButton = new JButton("장바구니에 담기");

        addButton.addActionListener(e -> {
            if (fries.isSelected()) order.addSideToCart("감자튀김", 3000);
            if (cheeseStick.isSelected()) order.addSideToCart("치즈스틱", 4000);
            if (wings.isSelected()) order.addSideToCart("윙", 5000);

            JOptionPane.showMessageDialog(null, "사이드 메뉴가 장바구니에 담겼습니다!");
            sideFrame.dispose();
        });

        panel.add(fries);
        panel.add(cheeseStick);
        panel.add(wings);
        panel.add(addButton);

        sideFrame.getContentPane().add(panel);
        sideFrame.setVisible(true);
    }

    // 음료 선택 화면
    private void openDrinkSelection() {
        JFrame drinkFrame = new JFrame("음료 선택");
        drinkFrame.setSize(500, 300);
        drinkFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        drinkFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JCheckBox cola = new JCheckBox("콜라(2000원)");
        JCheckBox cider = new JCheckBox("사이다(2000원)");
        JCheckBox water = new JCheckBox("에이드(4000원)");

        JButton addButton = new JButton("장바구니에 담기");

        addButton.addActionListener(e -> {
            if (cola.isSelected()) order.addDrinkToCart("콜라", 2000);
            if (cider.isSelected()) order.addDrinkToCart("사이다", 2000);
            if (water.isSelected()) order.addDrinkToCart("에이드", 4000);

            JOptionPane.showMessageDialog(null, "음료가 장바구니에 담겼습니다!");
            drinkFrame.dispose();
        });

//        });

        panel.add(cola);
        panel.add(cider);
        panel.add(water);
        panel.add(addButton);

        drinkFrame.getContentPane().add(panel);
        drinkFrame.setVisible(true);
    }

    // 장바구니 확인
    private void displayCart() {
        JFrame cartFrame = new JFrame("장바구니 내용");
        cartFrame.setSize(500, 500);
        cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cartFrame.setLocationRelativeTo(null);

        JTextArea cartTextArea = new JTextArea(order.displayCart());
        cartTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartTextArea);

        cartFrame.getContentPane().add(scrollPane);
        cartFrame.setVisible(true);
    }

    // 결제 화면
    public void openPaymentWindow() {
        JFrame paymentFrame = new JFrame("결제하기");
        paymentFrame.setSize(500, 500);  // 창 크기 조정
        paymentFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // 총 금액 라벨, 글씨 크기를 키움
        JLabel totalLabel = new JLabel("총 금액: " + order.calculateTotalPrice() + "원");
        totalLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));  // 총 금액의 글씨 크기 설정

        // 장바구니 목록을 표시할 JTextArea
        JTextArea cartTextArea = new JTextArea(order.displayCart());
        cartTextArea.setEditable(false);  // 수정 불가로 설정
        cartTextArea.setLineWrap(true);   // 자동 줄바꿈 설정
        cartTextArea.setWrapStyleWord(true);  // 단어 단위로 줄바꿈

        // 장바구니 목록에 스크롤 추가
        JScrollPane scrollPane = new JScrollPane(cartTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(350, 150));  // 스크롤 패널 크기 설정

        // 결제 버튼 패널 (GridLayout을 사용하여 버튼 크기 맞춤)
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));  // 버튼 사이 간격 조정

        JButton cardPaymentButton = new JButton("카드 결제");
        JButton cashPaymentButton = new JButton("현금 결제");
        JButton mobilePaymentButton = new JButton("모바일 페이 결제");

        // 버튼의 크기를 동일하게 설정
        Dimension buttonSize = new Dimension(200, 60);  // 버튼 크기 설정
        cardPaymentButton.setPreferredSize(buttonSize);
        cashPaymentButton.setPreferredSize(buttonSize);
        mobilePaymentButton.setPreferredSize(buttonSize);



        // 결제 완료 시 처리
        ActionListener paymentListener = e -> {
            String paymentMethod = "";
            if (e.getSource() == cardPaymentButton) {
                paymentMethod = "카드";

                // 카드번호 입력 받기
                String cardNumber = JOptionPane.showInputDialog(null, "카드 번호를 입력해주세요:");
                if (cardNumber != null && !cardNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "카드 번호 입력 완료: " + cardNumber);
                } else {
                    JOptionPane.showMessageDialog(null, "유효한 카드 번호를 입력해주세요.");
                    return; // 카드번호 입력이 잘못된 경우 결제를 중단합니다.
                }

            } else if (e.getSource() == cashPaymentButton) {
                paymentMethod = "현금";
            } else if (e.getSource() == mobilePaymentButton) {
                paymentMethod = "모바일 페이";
            }

            // 폰번호 입력 받기
            String phoneNumber = JOptionPane.showInputDialog(null, "포인트 적립할 폰번호를 입력해주세요:");
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "포인트가 적립되었습니다! \n 적립번호: " + phoneNumber);
            }

            JOptionPane.showMessageDialog(null, paymentMethod + " 결제가 완료되었습니다. \n 주문해주셔서 감사합니다!");
            order.clearCart();  // 결제 후 장바구니 초기화
            cartDisplay.setText("");  // 장바구니 내용 초기화
            paymentFrame.dispose();
        };

        cardPaymentButton.addActionListener(paymentListener);
        cashPaymentButton.addActionListener(paymentListener);
        mobilePaymentButton.addActionListener(paymentListener);


        // 버튼 패널에 버튼 추가
        buttonPanel.add(cardPaymentButton);
        buttonPanel.add(cashPaymentButton);
        buttonPanel.add(mobilePaymentButton);

        // 패널에 총 금액, 장바구니 스크롤, 결제 버튼 추가
        panel.add(totalLabel);
        panel.add(scrollPane);  // 스크롤 추가
        panel.add(buttonPanel);  // 버튼 패널 추가

        paymentFrame.getContentPane().add(panel);
        paymentFrame.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));

    }
}




