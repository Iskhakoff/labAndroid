package com.rubtsovm.netexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.rubtsovm.netexample.net.MarvelApi;
import com.rubtsovm.netexample.net.request.characters.model.CharacterDataWrapper;
import com.rubtsovm.netexample.utils.CredentialsUtils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

import static com.rubtsovm.netexample.utils.CredentialsUtils.public_key;
import static com.rubtsovm.netexample.utils.CredentialsUtils.ts;
import static rx.android.schedulers.AndroidSchedulers.mainThread;

public class CharacterActivity extends AppCompatActivity {

    @BindView(R.id.avatarOfCharacter)
    ImageView avatarOfCharacter;
    @BindView(R.id.nameAboutTv)
    TextView nameAboutTv;
    @BindView(R.id.descriptionAboutTv)
    TextView descriptionAboutTv;

    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        ButterKnife.bind(this);

        Intent getId = getIntent();
        String idCharacter = getId.getStringExtra("id");


        MarvelApi marvelApi = MarvelApi.getInstance();
        subscription = marvelApi.getCharacterById(idCharacter,ts,public_key, CredentialsUtils.getHash())
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread())
                .subscribe(new Subscriber<CharacterDataWrapper>() {
                               @Override
                               public void onCompleted() {
                                   Log.d("MainActivity", "onCompleted");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.e("MainActivity", "onError => " + e.getMessage());
                               }

                               @Override
                               public void onNext(CharacterDataWrapper response) {
                                   Log.d("MainActivity", "onNext => " + response);
                                   try{
                                       if(response.getData().getResults().size() > 0){
                                           String imagePath = response.getData().getResults().get(0).getThumbnail().getPath()+ "/standard_xlarge" + ".";
                                           String imageExtension =  response.getData().getResults().get(0).getThumbnail().getExtension();
                                           String imageUrl = imagePath + imageExtension;

                                           Picasso.get().load(imageUrl).into(avatarOfCharacter);
                                           nameAboutTv.setText(response.getData().getResults().get(0).getName());
                                           descriptionAboutTv.setText(response.getData().getResults().get(0).getDescription());
                                       }
                                   }catch (NullPointerException e){
                                       Log.e("MainActivity", "NullPointerException  => " + e.getMessage());
                                   }
                               }
                           }
                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
