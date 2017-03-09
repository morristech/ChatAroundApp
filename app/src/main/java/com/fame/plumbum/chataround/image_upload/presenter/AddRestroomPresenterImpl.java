package com.fame.plumbum.chataround.image_upload.presenter;

import com.fame.plumbum.chataround.image_upload.OnAddRestroomResponse;
import com.fame.plumbum.chataround.image_upload.model.AddRestroomProvider;
import com.fame.plumbum.chataround.image_upload.model.data.AddRestroomData;
import com.fame.plumbum.chataround.image_upload.model.data.AddRestroomRequestData;
import com.fame.plumbum.chataround.image_upload.view.AddRestroomView;

/**
 * Created by meghal on 6/3/17.
 */

public class AddRestroomPresenterImpl implements AddRestroomPresenter {

    private AddRestroomView addRestroomView;
    private AddRestroomProvider addRestroomProvider;

    public AddRestroomPresenterImpl(AddRestroomView addRestroomView, AddRestroomProvider addRestroomProvider) {
        this.addRestroomView = addRestroomView;
        this.addRestroomProvider = addRestroomProvider;
    }

    @Override
    public void addRestroom(AddRestroomRequestData addRestroomRequestData) {

        addRestroomView.showLoader(true);

        addRestroomProvider.addRestroom(addRestroomRequestData, new OnAddRestroomResponse() {
            @Override
            public void onSuccess(AddRestroomData addRestroomData) {

                if (addRestroomData.isSuccess()) {

                    addRestroomView.onRestroomAdded(addRestroomData);
                    addRestroomView.showMessage(addRestroomData.getMessage());
                } else {
                    addRestroomView.showMessage(addRestroomData.getMessage());
                }
                addRestroomView.showLoader(false);

            }

            @Override
            public void onFailure(String message) {
                addRestroomView.showLoader(false);
                addRestroomView.showMessage(message);
            }
        });


    }
}
