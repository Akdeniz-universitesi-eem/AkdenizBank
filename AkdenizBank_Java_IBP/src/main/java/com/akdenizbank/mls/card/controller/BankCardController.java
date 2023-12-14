package com.akdenizbank.mls.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akdenizbank.mls.account.service.BankAccountService;
import com.akdenizbank.mls.card.BankCard;
import com.akdenizbank.mls.card.service.BankCardService;
import com.akdenizbank.mls.generic.rest.GenericApiResponse;

@RestController
@RequestMapping("/api/v1/bank-cards")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService;

    //TODO : CREATE BANK CARD METHOD
    public GenericApiResponse createBankCard(@PathVariable String id) {
        BankCard bankCardInDB = this.bankCardService.getById(id);
        if (bankCardInDB != null) {
            throw new RuntimeException("Bank Card Exists");
        }
        BankCard bankCard = new BankCard();
        // Önce bankCard ve bankCardInDB nesnelerini doğru bir şekilde oluşturun
        bankCard = new BankCard();
        bankCardInDB = null;

// Ardından verileri kopyalayın
        if (bankCardInDB != null) {
            // Varsayılan değerleri atamak yerine, null kontrolü ekleyerek kopyalama işlemi yapın
            String nameOnCard = bankCardInDB.getNameoncard();
            bankCard.setNameoncard(nameOnCard != null ? nameOnCard : "");

            String cardNumber = bankCardInDB.getCardnumber();
            bankCard.setCardnumber(cardNumber != null ? cardNumber : "");

            String cvc = bankCardInDB.getCvc();
            bankCard.setCvc(cvc != null ? cvc : "");

            String expireDate = bankCardInDB.getExpiredate();
            bankCard.setExpiredate(expireDate != null ? expireDate : "");
        } else {
            // bankCardInDB null ise, gerekirse uygun bir işlem yapın (örneğin, bir hata durumu)
            System.out.println("Hata: bankCardInDB null.");
        }

        return new GenericApiResponse(200, "Success", "3457462945213", bankCard);
    }

    //TODO : UPDATE BANK CARD METHOD
    public GenericApiResponse updateBankCard(@PathVariable String id) {
        BankCard bankCardInDB = this.bankCardService.getById(id);
        if (bankCardInDB == null) {
            throw new RuntimeException("No Such Bank Card");
        }
        BankCard updatedBankCard = bankCardService.update(bankCardInDB);
        return new GenericApiResponse(200, "Success", "6667462945213", updatedBankCard);
    }


    @GetMapping
    public GenericApiResponse getAlBankCards(Pageable pageable) {
        Page<BankCard> bankCardsPage = this.bankCardService.getAll(pageable);
        return new GenericApiResponse(200, "Success", "345732945213", bankCardsPage);
    }

    @GetMapping("/{id}")
    public GenericApiResponse getById(@PathVariable String id) {
        BankCard bankCardInDB = this.bankCardService.getById(id);
        if (bankCardInDB == null) {
            throw new RuntimeException("No Such Bank Card");
        }
        return new GenericApiResponse(200, "Success", "23097452893", bankCardInDB);
    }

    @DeleteMapping("/{id}")
    public GenericApiResponse deleteCard(@PathVariable String id) {
        this.bankCardService.delete(id);
        return new GenericApiResponse(200, "Success", "34265782");
    }

}
