package com.wallet;

import com.wallet.constants.Constant;
import com.wallet.service.WalletService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        Scanner input = new Scanner(new File(args[0]));

        WalletService walletService = new WalletService(3);

        while (input.hasNextLine()) {
            String line = input.nextLine().trim();
            String[] commands = line.split(" ");
            switch (commands[0]) {
                case Constant.CREATE_WALLET:
                    if (commands.length != 3) {
                        System.out.println(Constant.INVALID_INPUT);
                        break;
                    }
                    walletService.createWallet(commands[1], Double.parseDouble(commands[2]));
                    break;
                case Constant.TRANSFER_MONEY:
                    if (commands.length != 4) {
                        System.out.println(Constant.INVALID_INPUT);
                        break;
                    }
                    walletService.transferMoney(commands[1], commands[2], Double.parseDouble(commands[3]));
                    break;
                case Constant.OVERVIEW:
                    if (commands.length != 1) {
                        System.out.println(Constant.INVALID_INPUT);
                        break;
                    }
                    walletService.getOverview();
                    break;
                case Constant.STATEMENT:
                    if (commands.length != 2) {
                        System.out.println(Constant.INVALID_INPUT);
                        break;
                    }
                    walletService.getStatementByUserName(commands[1]);
                    break;
                case Constant.OFFER2:
                    if (commands.length != 1) {
                        System.out.println(Constant.INVALID_INPUT);
                        break;
                    }
                    walletService.fireOffer2();
                    break;
                case Constant.FIXED_DEPOSIT:
                    if (commands.length != 3) {
                        System.out.println(Constant.INVALID_INPUT);
                        break;
                    }
                    walletService.fixedDeposit(commands[1], Double.parseDouble(commands[2]));
                    break;
            }
        }
    }
}
