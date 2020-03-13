package com.technohub.melifeapp.Interfaces;

public interface IInstruction {
    interface  View
    {
        void init();

        void initClicks();

        void showLoading();

        void hideLoading();

        void loadExamFragment();
    }
    interface Presenter
    {
        void created();

        void proceedButtonClick(String sec_id,String exam_id);



    }
}
