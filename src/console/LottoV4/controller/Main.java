package console.LottoV4.controller;

import console.LottoV4.domain.Buyer;
import console.LottoV4.view.InputView;

public class Main {
    public static void main(String[] args) {
        Buyer purchase = InputView.purchase();
        System.out.println(purchase.getNumberOfTickets());


    }



}
