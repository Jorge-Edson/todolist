package br.com.jorgeadao.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {
    
    //Merge values for partial update
    public static void CopyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    //Get all null properties
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptynames = new HashSet<>();

        //Get old values
        for(PropertyDescriptor pd: pds) {
            Object srcValue = src.getPropertyValue(pd.getName());

            if(srcValue == null) {
                emptynames.add(pd.getName());
            }
        }

        String[] result = new String[emptynames.size()];

        return emptynames.toArray(result);
    }
}
