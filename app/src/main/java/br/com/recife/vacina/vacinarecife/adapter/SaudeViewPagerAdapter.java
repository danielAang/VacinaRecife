package br.com.recife.vacina.vacinarecife.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.recife.vacina.vacinarecife.activity.PostosActivity;
import br.com.recife.vacina.vacinarecife.activity.VacinasActivity;

/**
 * Created by morae on 11/01/2018.
 */

public class SaudeViewPagerAdapter extends FragmentStatePagerAdapter {

    private int tabsCount;
    private String[] titles = new String[]{"Vacinas", "Postos de Vacinação"};

    public SaudeViewPagerAdapter(FragmentManager fm, int tabsCount) {
        super(fm);
        this.tabsCount = tabsCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new VacinasActivity();
            case 1:
                return new PostosActivity();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabsCount;
    }
}
