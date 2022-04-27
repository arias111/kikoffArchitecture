package com.itis.kikoff;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Data {

    private String[] basketExpected = {"Успешное получение данных о корзине", "Успешное создание корзины", "Успешное удаление корзины"};
    private String[] categoryExpected= {"Успешное получение списка всех категорий", "Успешное получение продукта определенной категории",
            "Успешное создание категории"};
    private String[] deliveryExpected = {"Успешное сохранение адреса"};
    private String[] productAndBasketExpected = {"Продукт добавлен в корзину", "Продукт удален из корзины",
        "Успешное получение всех продуктов из корзины"};
    private String[] productExpected = {"Новый продукт сохранен", "Продукт удален", "Продукт получен"};
    private String[] purchaseExpected = {"Покупка успешно завершена"};
    private String[] purchasePreparationExpected= {"Чек создан", "Чек заполнен"};

    private String[] basketHeads = {"Ошибка при получении данных о корзине", "Ошибка создания корзины", "Ошибка удаления корзины"};
    private String[] categoryHeads = {"Ошибка при получении всех категорий", "Ошибка при получении продукта определенной категории",
        "Ошибка при создании новой категории"};
    private String[] deliveryHeads = {"Ошибка при сохранении адреса"};
    private String[] productAndBasketHeads = {"Ошибка при добавлении продукта в корзину", "Ошибка при удалении продукта из корзины",
        "Ошибка при получении всех продуктов из корзины"};
    private String[] productHeads = {"Ошибка при добавлении продукта", "Ошибка при удалении продукта", "Ошибка при получении продукта"};
    private String[] purchaseHeads = {"Ошибка при завершении покупки"};
    private String[] purchasePreparationHeads = {"Ошибка при создании чека", "Ошибка при заполнении чека"};

    private String[] moduleList = {"BasketController", "CategoryController", "DeliveryController",
            "ProductAndBasketController", "ProductController", "PurchaseController", "PurchasePreparationController"};

    public ArrayList<DataBean> getDataBeanList(String actualResult)
    {
        ArrayList<DataBean> dataBeanList = new ArrayList<>();

        dataBeanList.add(new DataBean(basketHeads[0], moduleList[0], actualResult, basketExpected[0]));
        dataBeanList.add(new DataBean(basketHeads[1], moduleList[0], actualResult, basketExpected[1]));
        dataBeanList.add(new DataBean(basketHeads[2], moduleList[0], actualResult, basketExpected[2]));

        dataBeanList.add(new DataBean(categoryHeads[0], moduleList[1], actualResult, categoryExpected[0]));
        dataBeanList.add(new DataBean(categoryHeads[1], moduleList[1], actualResult, categoryExpected[1]));
        dataBeanList.add(new DataBean(categoryHeads[2], moduleList[1], actualResult, categoryExpected[2]));

        dataBeanList.add(new DataBean(deliveryHeads[0], moduleList[2], actualResult, deliveryExpected[0]));

        dataBeanList.add(new DataBean(productAndBasketHeads[0], moduleList[3], actualResult, productAndBasketExpected[0]));
        dataBeanList.add(new DataBean(productAndBasketHeads[1], moduleList[3], actualResult, productAndBasketExpected[1]));
        dataBeanList.add(new DataBean(productAndBasketHeads[2], moduleList[3], actualResult, productAndBasketExpected[2]));

        dataBeanList.add(new DataBean(productHeads[0], moduleList[4], actualResult, productExpected[0]));
        dataBeanList.add(new DataBean(productHeads[1], moduleList[4], actualResult, productExpected[1]));
        dataBeanList.add(new DataBean(productHeads[2], moduleList[4], actualResult, productExpected[2]));

        dataBeanList.add(new DataBean(purchaseHeads[0], moduleList[5], actualResult, purchaseExpected[0]));

        dataBeanList.add(new DataBean(purchasePreparationHeads[0], moduleList[6], actualResult, purchasePreparationExpected[0]));
        dataBeanList.add(new DataBean(purchasePreparationHeads[1], moduleList[6], actualResult, purchasePreparationExpected[1]));

        return dataBeanList;
    }

//    private Date date(int day)
//    {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, 2022);
//        calendar.set(Calendar.MONTH, Calendar.);
//        calendar.set(Calendar.DAY_OF_MONTH, day);
//        return calendar.getTime();
//    }
}
