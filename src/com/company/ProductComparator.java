package com.company;

import java.util.Comparator;

public class ProductComparator implements Comparator<product> {

    @Override
    public int compare(product o1, product o2) {
            return Double.compare(o1.getPrice(),o2.getPrice());
    }
}
