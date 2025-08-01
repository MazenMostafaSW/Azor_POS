/*
**    Chromis Administration  - Open Source Point of Sale
**
**    This file is part of Chromis Administration Version Chromis V1.5.4
**
**    Copyright (c) 2015-2023 Chromis & previous Openbravo POS related works   
**
**    https://www.chromis.co.uk
**   
**    Chromis POS is free software: you can redistribute it and/or modify
**    it under the terms of the GNU General Public License as published by
**    the Free Software Foundation, either version 3 of the License, or
**    (at your option) any later version.
**
**    Chromis POS is distributed in the hope that it will be useful,
**    but WITHOUT ANY WARRANTY; without even the implied warranty of
**    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
**    GNU General Public License for more details.
**
**    You should have received a copy of the GNU General Public License
**    along with Chromis POS.  If not, see <http://www.gnu.org/licenses/>
**
*/


package uk.chromis.custom;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.util.HashMap;
import java.util.List;
import uk.chromis.commons.utils.KeyedData;

public class ExtendedComboBox extends ComboBox {

    private HashMap<String, Integer> indexMap = new HashMap<String, Integer>();
    private HashMap<String, String> nameMap = new HashMap<String, String>();

    public ExtendedComboBox() {
        super();
    }

    public ExtendedComboBox(List<KeyedData> list) {
        ObservableList<KeyedData> obsList = FXCollections.observableArrayList(list);
        setItemList(obsList);
    }

    public void setItemList(List<KeyedData> list) {
        ObservableList<KeyedData> obsList = FXCollections.observableArrayList(list);
        setItemList(obsList);
    }

    public void setItemListWithNull(List<KeyedData> list) {
        ObservableList<KeyedData> obsList = FXCollections.observableArrayList();
        obsList.add(new KeyedData("null", ""));
        obsList.addAll(list);
        setItemList(obsList);
    }

    public void setItemListWithAll(List<KeyedData> list) {
        ObservableList<KeyedData> obsList = FXCollections.observableArrayList();
        obsList.add(new KeyedData("**all**", "All"));
        obsList.addAll(list);
        setItemList(obsList);
    }

    public ExtendedComboBox(ObservableList<KeyedData> list) {
        int i = 0;
        for (KeyedData k : list) {
            indexMap.put(k.idProperty().getValue(), i);
            nameMap.put(k.idProperty().getValue(), k.nameProperty().getValue());
            i++;
        }

        StringConverter<KeyedData> converter = new StringConverter<KeyedData>() {
            @Override
            public String toString(KeyedData object) {
                return object.nameProperty().get();
            }

            @Override
            public KeyedData fromString(String id) {
                return null;
            }
        };

        this.setConverter(converter);
        this.setItems(list);
    }

    public void setItemList(ObservableList<KeyedData> list) {
        int i = 0;
        for (KeyedData k : list) {
            indexMap.put(k.idProperty().getValue(), i);
            nameMap.put(k.idProperty().getValue(), k.nameProperty().getValue());
            i++;
        }

        StringConverter<KeyedData> converter = new StringConverter<KeyedData>() {
            @Override
            public String toString(KeyedData object) {            
                if (object == null){
                    return null;
                }
                return object.nameProperty().get();
            }

            @Override
            public KeyedData fromString(String id) {
                return null;
            }
        };

        this.setConverter(converter);
        this.setItems(list);

    }
    
    public String getDescription(String id) {
        return nameMap.get(id);
    }

    public Integer getIndex(String id) {
        if (id != null) {
            return indexMap.get(id);
        }
        return null;
    }

    public void setSelectedItem(String id) {
        if (id != null) {
            int j = getIndex(id);
            getSelectionModel().select(j);
        } else {
            getSelectionModel().clearSelection();
        }
    }

    public String getSelectedKey() {
        KeyedData keyed = (KeyedData) this.getSelectionModel().getSelectedItem();
        if (keyed == null || keyed.getId().equals("null")) {
            return null;
        }
        return keyed.idProperty().getValue();
    }

    public void setIndex(String id) {
        this.getSelectionModel().select(indexMap.get(id));
    }

    public void setIndex(Integer id) {
        this.getSelectionModel().select(id);
    }

    public String getItem() {
        KeyedData keyed = (KeyedData) this.getSelectionModel().getSelectedItem();
        return keyed.nameProperty().getValue();
    }

//        public String getItem() {
//        KeyedData keyed = (KeyedData) this.getSelectionModel().getSelectedItem();
//        if (keyed.nameProperty().getValue()){
//        }
//        }
//        return keyed.nameProperty().getValue();
//    }
    
}
