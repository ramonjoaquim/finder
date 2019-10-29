package com.quintus.labs.datingapp.Main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.quintus.labs.datingapp.Login.Login;
import com.quintus.labs.datingapp.Matched.Matched_Activity;
import com.quintus.labs.datingapp.R;
import com.quintus.labs.datingapp.Utils.PulsatorLayout;
import com.quintus.labs.datingapp.Utils.TopNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Quintus Labs on 19-Dec-2018.
 * www.quintuslabs.com
 * edited By Ramon Joaquim Limas
 */
public class MainActivity extends Activity {



    public static int contLikeButton = 0;
    public static int contDislikeButton = 0;
    public static String message = "";
    ProgressBar progressBar;
    public static boolean verificaNovoUsuario = false;

    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 1;
    final private int MY_PERMISSIONS_REQUEST_LOCATION = 123;
    ListView listView;
    List<Cards> rowItems;
    FrameLayout cardFrame, moreFrame;
    private Context mContext = MainActivity.this;
    private NotificationHelper mNotificationHelper;
    private Cards cards_data[];
    private PhotoAdapter arrayAdapter;


    public String instituicao = "Faculdades Esucri";

    //imagens links
    public String java = "https://yt3.ggpht.com/a/AGF-l7-rdc6ISv9vI8nuUyvlbDIdkctNkU6cVxCZ5w=s900-mo-c-c0xffffffff-rj-k-no"; // dimensao 620x380
    public String arduino = "https://static.interestingengineering.com/images/FEBRUARY/sizes/arduino-1_resize_md.jpg";
    public String internetDasCoisas = "http://www.engineersrule.com/wp-content/uploads/2016/02/IIot3.png.jpg";
    public String empreendedorismo = "https://cdn.pixabay.com/photo/2018/10/08/12/08/business-3732633_960_720.png";
    public String admRede = "http://www.fastbrickrobotics.net/wp-content/uploads/2017/03/Data-Center-connection.jpg";
    public String analise = "http://arquivo.devmedia.com.br/marketing/img/artigo-atividades-basicas-ao-processo-de-desenvolvimento-de-software-5413.png";
    public String algoritimos = "https://proexe-www.s3.amazonaws.com/282/zdj1.png";
    public String ia = "https://csis-prod.s3.amazonaws.com/s3fs-public/publication/180118_ai_skynet_lewis_2.jpg";
    public String qualidade = "https://cdn.pixabay.com/photo/2014/07/30/22/56/workstation-405768_960_720.jpg";
    public String banco = "http://www.plsystem.com.br/wp-content/uploads/2017/03/database-wallpapers-024.jpg";


    //textos  bio

    public String textoJava = "Java é uma linguagem de programação e plataforma computacional lançada pela primeira vez pela Sun Microsystems em 1995.\nExistem muitas aplicações e sites que não funcionarão, a menos que você tenha o Java instalado, e mais desses são criados todos os dias.\nO Java é rápido, seguro e confiável. De laptops a datacenters, consoles de games a supercomputadores científicos, telefones celulares à Internet, o Java está em todos os lugares! Eai, bora aprender?!";
    public String textoArduino = "As placas Arduino possui funcionamento semelhante ao de um pequeno computador, no qual, pode-se programar a maneira como suas entradas e saídas devem se comportar em meio aos diversos componentes externos que podem ser conectados nas mesmas.\n" +
            "\n" +
            "Com este curso você irá aprender sobre automações, onde pode ser aplicada em sua própria residência, como por exemplo, ligar uma lâmpada utilizando seu próprio celular, entre outras, o limite é sua imaginação.\n";
    public String textointernetDasCoisas = "Internet das coisas é um conceito tecnológico em que todos os objetos da vida cotidiana estariam conectados à internet, agindo de modo inteligente e sensorial.\n" +
            "Também conhecida por IoT (Internet of Things, em inglês), consiste na ideia da fusão do “mundo real” com o “mundo digital”, fazendo com que o indivíduo possa estar em constante comunicação e interação, seja com outras pessoas ou objetos.\n";

    public String textoEmpreendorismo = "Significa empreender, resolver um problema ou situação complicada.\nÉ um termo muito usado no âmbito empresarial e muitas vezes está relacionado com a criação de empresas ou produtos novos.\n Empreender é também agregar valor, saber identificar oportunidades e transformá-las em um negócio lucrativo.";

    public String textoadmRede = "Administrador de Redes por ser o profissional responsável por atuar com desenvolvimento de soluções e administração de servidores Windows / Vmware e redes de dados em ambiente Data Center se relaciona com toda área de redes.\n Realizar por conta própria suas instalações de redes desejadas, sem a necessidade de chamar “O cara”.";
    public String textoalgoritimo= "Todas as tarefas executadas pelo computador, são baseadas em Algoritmos.\nLogo, um algoritmo deve também ser bem definido, pois é uma máquina que o executará. Uma calculadora por exemplo, para executar a operação de multiplicação, executa um algoritmo que calcula somas até um determinado número de vezes.\nO simples ato de atravessar a rua requer um algoritmo, com um computador não é diferente, ao concluir este curso, irá você entender o conceito e a começar a engatinhar no ramo de programação.\n";
    public String textoIA = "Inteligência artificial (artificial intelligence - A.I., em inglês) é um ramo de pesquisa da Ciência da Computação que se ocupa em desenvolver mecanismos e dispositivos tecnológicos que possam simular o raciocínio humano, ou seja, a inteligência que é característica dos seres humanos.\nInteligência Artificial é o futuro senhoras e senhores, e quem o domina irá dominar o mundo!\n";
    public String textobanco = "Existem vários tipos de banco de dados e eles estão presentes na nossa vida há muito tempo, a lista telefônica por exemplo pode ser considerada um banco de dados. \n" +
            "Antigamente as empresas armazenavam informações em arquivos físicos, mas o surgimento e evolução dos computadores possibilitaram o armazenamento de dados de modo digital. \nAssim os bancos de dados evoluíram e se tornaram o coração de muitos sistemas de informação. \n";
    public String textoqualidade = "Qualidade de software é uma área do conhecimento da engenharia de software que pode se referir a: \"as características desejadas de produtos de software, a extensão em que um produto de software em particular possui essas características e aos processos, ferramentas e técnicas que são usadas para garantir essas características.\n";

    public String textoanalise = "Análise e projetos de sistemas fazem parte da engenharia de software.\nAmbas são disciplinas de modelagem, a análise antecede o projeto e  deve certificar que conhece os requisitos funcionais e não funcionais do sistema";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        final ImageView ivBasicImage =  findViewById(R.id.introducao);
        ivBasicImage.setVisibility(View.VISIBLE);

        final FrameLayout layout = findViewById(R.id.card_frame);
        layout.setVisibility(View.GONE);


        cardFrame = findViewById(R.id.card_frame);
        moreFrame = findViewById(R.id.more_frame);



        // start pulsator
        //PulsatorLayout mPulsator = findViewById(R.id.pulsator);
        //mPulsator.start();
        mNotificationHelper = new NotificationHelper(this);



        setupTopNavigationView();

        //se curtiu mais de 6 da match com sistema de informação
        //se acabar as opções e os likes forem < 6 messagem "continue pesquisando seu curso"

        rowItems = new ArrayList<Cards>();
        Cards cards = new   Cards("1", "Java", instituicao, java, textoJava, "", 10);
        rowItems.add(cards);
        cards = new Cards("2", "Empreendorismo", instituicao, empreendedorismo, textoEmpreendorismo, "", 10);
        rowItems.add(cards);
        cards = new Cards("3", "Algoritimos e Programação", instituicao, algoritimos, textoalgoritimo, "", 10);
        rowItems.add(cards);
        cards = new Cards("4", "Inteligência Artificial", instituicao, ia, textoIA, "", 10);
        rowItems.add(cards);
        cards = new Cards("5", "Internet Das Coisas", instituicao, internetDasCoisas, textointernetDasCoisas, "", 10);
        rowItems.add(cards);
        cards = new Cards("6", "Análise E Projeto De Sistemas", instituicao, analise, textoanalise, "", 10);
        rowItems.add(cards);
        cards = new Cards("7", "Administração de Redes", instituicao, admRede, textoadmRede, "", 10);
        rowItems.add(cards);
        cards = new Cards("8", "Arduino", instituicao, arduino, textoArduino, "", 10);
        rowItems.add(cards);
        cards = new Cards("9", "Banco De Dados", instituicao, banco, textobanco, "", 10);
        rowItems.add(cards);
        cards = new Cards("10", "Engenharia E Qualidade de Software", instituicao, qualidade, textoqualidade, "", 10);
        rowItems.add(cards);



        arrayAdapter = new PhotoAdapter(this, R.layout.item, rowItems);
        if (verificaNovoUsuario == false){

            try{
                // esperar fazer a introdução
                Glide.with(mContext)
                        .asGif()
                        .load("https://media0.giphy.com/media/ZgVV3mOFPTRwn58pST/source.gif")
                        .into(ivBasicImage);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(7000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ivBasicImage.setVisibility(View.GONE);
                                layout.setVisibility(View.VISIBLE);

                                checkRowItem();
                                updateSwipeCard();
                            }
                        });
                    }
                }).start();
            }catch (Exception e ){
                ivBasicImage.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);

                checkRowItem();
                updateSwipeCard();
            }
        }else{
            verificaNovoUsuario = true;
            ivBasicImage.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);

            checkRowItem();
            updateSwipeCard();
        }




    }





    private void checkRowItem() {
        if (rowItems.isEmpty()) {
            moreFrame.setVisibility(View.VISIBLE);
            cardFrame.setVisibility(View.GONE);
            matchSistemaInformacao();
        }
    }

    private void updateLocation() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {

        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        updateLocation();
                    } else {
                        Toast.makeText(MainActivity.this, "Location Permission Denied. You have to give permission inorder to know the user range ", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            }

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void updateSwipeCard() {
        final SwipeFlingAdapterView flingContainer = findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;
                contDislikeButton++;
                checkRowItem();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Cards obj = (Cards) dataObject;
                contLikeButton++;
                //check matches
                checkRowItem();

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here


            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                //achei viado
            }
        });
    }


    public void sendNotification() {
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification(mContext.getString(R.string.app_name), mContext.getString(R.string.match_notification));

        mNotificationHelper.getManager().notify(1, nb.build());
    }

    public void matchSistemaInformacao(){
        if (contLikeButton >= 6 ){
            message = ("Match com Sistema de Informação!!");
            contLikeButton = 0;
            Toasty.success(mContext, message, Toast.LENGTH_LONG).show();
            sendNotification();
            Intent intent = new Intent(MainActivity.this, Matched_Activity.class);
            startActivity(intent);

        }else{
            message = ("Continue procurando seu curso... tente novamente!");
            contDislikeButton = 0;
            Toasty.info(mContext, message, Toast.LENGTH_LONG).show();

                  }

    }

    public void DislikeBtn(View v) {
        if (rowItems.size() != 0) {
            Cards card_item = rowItems.get(0);

            String userId = card_item.getUserId();

            rowItems.remove(0);
            arrayAdapter.notifyDataSetChanged();
            contDislikeButton++;

            Intent btnClick = new Intent(mContext, BtnDislikeActivity.class);
            btnClick.putExtra("url", card_item.getProfileImageUrl());
            startActivity(btnClick);
        }

        if(rowItems.size() == 0){
            matchSistemaInformacao();
        }
    }

    public void LikeBtn(View v) {
        if (rowItems.size() != 0) {
            Cards card_item = rowItems.get(0);

            String userId = card_item.getUserId();

            //check matches
            //achei viado

            rowItems.remove(0);
            arrayAdapter.notifyDataSetChanged();
            contLikeButton++;


            Intent btnClick = new Intent(mContext, BtnLikeActivity.class);
            btnClick.putExtra("url", card_item.getProfileImageUrl());
            startActivity(btnClick);
        }
        if(rowItems.size() == 0){
            matchSistemaInformacao();
        }
    }

    /***************************************************************/




    /**
     * setup top tool bar
     */
    private void setupTopNavigationView() {
        Log.d(TAG, "setupTopNavigationView: setting up TopNavigationView");
        BottomNavigationViewEx tvEx = findViewById(R.id.topNavViewBar);
        TopNavigationViewHelper.setupTopNavigationView(tvEx);
        TopNavigationViewHelper.enableNavigation(mContext, tvEx);
        Menu menu = tvEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


    @Override
    public void onBackPressed() {

    }





}
