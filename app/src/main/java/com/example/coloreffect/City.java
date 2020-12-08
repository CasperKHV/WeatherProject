package com.example.coloreffect;

public class City {

    private String name;
    private int imageResourceId;


    private City(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    String getName() {
        return name;
    }


    int getImageResourceId() {
        return imageResourceId;
    }

    // nails - массив с элементами Nail
    // текст должен браться из ресурсов, а здесь просто для примера так сделано, это хардкод, так нельзя!!!
    static final City[] nails = {
            new City("Классический маникюр",
                    R.drawable.khv_image),
            new City("Аппаратный маникюр",
                    R.drawable.moscow_image),
            new City("Аппаратный педикюр",
                    R.drawable.spb_image)};
}



