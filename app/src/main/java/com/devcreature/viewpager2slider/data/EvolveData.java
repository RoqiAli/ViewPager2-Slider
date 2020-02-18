package com.devcreature.viewpager2slider.data;

import com.devcreature.viewpager2slider.R;
import com.devcreature.viewpager2slider.model.EvolveModel;

import java.util.ArrayList;

public class EvolveData {

    private static int[] IMAGE = {
            R.drawable.charmander,
            R.drawable.charmeleon,
            R.drawable.charizard,
            R.drawable.mega_charizard_x,
            R.drawable.mega_charizard_y,
            R.drawable.charizard_gigantamax,
    };

    private static String[] NAME = {
            "Charmander",
            "Charmeleon",
            "Charizard",
            "Mega Charizard X",
            "Mega Charizard Y",
            "Charizard Gigantamax"
    };

    private static String[] DESC = {
            "The flame that burns at the tip of its tail is an indication of its emotions. The flame wavers when Charmander is enjoying itself. If the Pokémon becomes enraged, the flame burns fiercely.",
            "Charmeleon mercilessly destroys its foes using its sharp claws. If it encounters a strong foe, it turns aggressive. In this excited state, the flame at the tip of its tail flares with a bluish white color.",
            "Charizard flies around the sky in search of powerful opponents. It breathes fire of such great heat that it melts anything. However, it never turns its fiery breath on any opponent weaker than itself.",
            "The overwhelming power that fills its entire body causes it to turn black and create intense blue flames.",
            "Its bond with its Trainer is the source of its power. It boasts speed and maneuverability greater than that of a jet fighter.",
            "The flame inside its body burns hotter than 3,600 degrees Fahrenheit. When Charizard roars, that temperature climbs even higher."
    };

    public static ArrayList<EvolveModel> getListData() {
        ArrayList<EvolveModel> list = new ArrayList<>();
        for (int position = 0; position < IMAGE.length; position++) {
            EvolveModel hero = new EvolveModel();
            hero.setPhoto(IMAGE[position]);
            hero.setName(NAME[position]);
            hero.setDesc(DESC[position]);
            list.add(hero);
        }
        return list;
    }
}
