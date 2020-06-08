package com.example.ioun25;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.ioun25.MainActivity.gYparxoyses;

public class parameters extends AppCompatActivity {
GridView moviesList;
    public List<EIDOS> listOfEIDOS = new ArrayList<EIDOS>();
    GridView prosueta;
public ArrayList<String> values;
   public String fID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);


        ListView list = (ListView) findViewById(R.id.listEIDH);
        list.setClickable(true);

Show();



        parameters.EIDHadapter adapter = new parameters.EIDHadapter(parameters.this, listOfEIDOS);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                //  System.out.println("sadsfsf");

               String a=listOfEIDOS.get(position).getName();


                EditText t1=findViewById(R.id.t1);  // timh
                t1.setText(listOfEIDOS.get(position).getTimh().toString());
                EditText tp=findViewById(R.id.Timhp);
                tp.setText(listOfEIDOS.get(position).getTimhp().toString()); //kathg

                EditText t2=findViewById(R.id.t2);  // onoma
                t2.setText(listOfEIDOS.get(position).getName());
                fID=Integer.toString(listOfEIDOS.get(position).getID());  //id
                EditText t4=findViewById(R.id.t4);//prosu
                t4.setText(listOfEIDOS.get(position).getProsu());
                EditText t5=findViewById(R.id.t5);
                t5.setText(listOfEIDOS.get(position).getKathg()); //kathg
                show_prosueta();

            }
        });

        list.setAdapter(adapter);


/*
        // ΔΙΑΛΕΓΩ ΤΟ ΤΡΑΠΕΖΙ ΠΟΥ ΘΕΛΩ
        moviesList=(GridView)findViewById(R.id.listEidhp);
        moviesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // ΤΙΜΗ
                int mID ;
                mID=position - position%5+2;
                Object o = moviesList.getItemAtPosition(mID);
                EditText t1=findViewById(R.id.t1);
                t1.setText(o.toString());
               // ΟΝΟΜΑ
                int mID2 ;
                mID2=position - position%5+1;
                Object o2 = moviesList.getItemAtPosition(mID2);

                EditText t2=findViewById(R.id.t2);
                t2.setText(o2.toString());
                // ΙD
                int mID3 ;
                mID3=position - position%5;
                Object o3 = moviesList.getItemAtPosition(mID3);
                fID=o3.toString();
                // ΠΡΟΣΘΕΤΑ
                int mID4 ;
                mID4=position - position%5+3;
                Object o4 = moviesList.getItemAtPosition(mID4);

                EditText t4=findViewById(R.id.t4);
                t4.setText(o4.toString());

                // ΠΡΟΣΘΕΤΑ
                int mID5 ;
                mID4=position - position%5+4;
                Object o5 = moviesList.getItemAtPosition(mID4);

                EditText t5=findViewById(R.id.t5);
                t5.setText(o5.toString());
                show_prosueta();

            }
        });

        Show();
     */



    }
  /*  public void KATHGORIES_intent (View view) {

        Intent intent = new Intent(this, KATHGORIES.class);
        //  EditText editText = (EditText) findViewById(R.id.editText);

        startActivity(intent);
        // Do something in response to button
    };
*/


    public void SAVE (View view) {
        EditText t1=findViewById(R.id.t1);
        EditText t2=findViewById(R.id.t2);
        EditText t4=findViewById(R.id.t4);
        EditText t5=findViewById(R.id.t5);
        EditText tp=findViewById(R.id.Timhp);
        SQLiteDatabase mydatabase=null;

         mydatabase = openOrCreateDatabase("eidh",MODE_PRIVATE,null);
        mydatabase.execSQL("update EIDH set  num1="+tp.getText()+", timh="+t1.getText()+",ONO='"+t2.getText()+"',CH2='"+t4.getText()+"',CH1='"+t5.getText()+"' where ID="+fID);

        Show();
    }
    public void NEO_EIDOS (View view) {
        EditText t1=findViewById(R.id.t1);
        EditText t2=findViewById(R.id.t2);
        EditText t4=findViewById(R.id.t4);
        EditText t5=findViewById(R.id.t5);
        SQLiteDatabase mydatabase=null;

        mydatabase = openOrCreateDatabase("eidh",MODE_PRIVATE,null);
        String sql;
        sql="INSERT INTO  EIDH ( timh,ONO,CH2,ch1) VALUES ("+t1.getText()+",'"+t2.getText()+"','"+t4.getText()+"','"+t5.getText()+"');";
        mydatabase.execSQL(sql);
        Show();
    }
    public void DELETE (View view) {
        EditText t1 = findViewById(R.id.t1);
        EditText t2 = findViewById(R.id.t2);
        EditText t4 = findViewById(R.id.t4);
        SQLiteDatabase mydatabase = null;

        mydatabase = openOrCreateDatabase("eidh", MODE_PRIVATE, null);
        mydatabase.execSQL("DELETE FROM  EIDH  where ID=" + fID);

        Show();
    }
    public void Show () {

        SQLiteDatabase mydatabase=null;
        Integer n=0;
        moviesList=(GridView)findViewById(R.id.listEidhp);
        //recyclerView=(RecyclerView) findViewById(R.id.grid2);
        //   List<String> values=new ArrayList<>();
        values=new ArrayList<String>();

        try{
            /*mydatabase = openOrCreateDatabase("eidh",MODE_PRIVATE,null);
            Cursor cursor2 = mydatabase.rawQuery("select ID,ONO,TIMH,CH2,CH1  from  EIDH", null);
            String kat="";
            String syn="";
            if (cursor2.moveToFirst()) {
                do {
                    values.add( Integer.toString(cursor2.getInt(0) ));
                    values.add(cursor2.getString(1));
                    values.add( Double.toString(cursor2.getDouble(2) ));
                    values.add(cursor2.getString(3));
                    values.add(cursor2.getString(4));
                } while (cursor2.moveToNext());
            }
            mydatabase.close();
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);
            moviesList.setAdapter(arrayAdapter);
*/

            listOfEIDOS = new ArrayList<EIDOS>();

            mydatabase = openOrCreateDatabase("eidh",MODE_PRIVATE,null);
            Cursor cursor2 = mydatabase.rawQuery("select ID,ONO,TIMH,IFNULL(CH2,'') AS CCH2,IFNULL(CH1,'') AS CCH1 ,IFNULL(num1,0) AS TIMHP  from  EIDH ORDER BY ID DESC", null);
            String kat="";
            String syn="";
            if (cursor2.moveToFirst()) {
                do {
                    listOfEIDOS.add(new EIDOS(cursor2.getInt(0) , cursor2.getString(1),cursor2.getDouble(2),cursor2.getString(3),cursor2.getString(4),cursor2.getDouble(5),0));
                } while (cursor2.moveToNext());
            }
            mydatabase.close();

            parameters.EIDHadapter adapter = new parameters.EIDHadapter(parameters.this, listOfEIDOS);

            ListView list = (ListView) findViewById(R.id.listEIDH);
            list.setAdapter(adapter);














            // TrapeziaList.setAdapter(arrayAdapter);

            //final MyAdapter adapter = new MyAdapter();
            //  rv.setAdapter(adapter);
            //   GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            //  rv.setLayoutManager(mLayoutManager);

// set up the RecyclerView
            //   RecyclerView recyclerView = findViewById(R.id.rvAnimals);
            //    TrapeziaList.setLayoutManager(new LinearLayoutManager(this));
            //   adapter = new MyRecyclerViewAdapter(this, animalNames);
            //   adapter.setClickListener(this);
            //   recyclerView.setAdapter(adapter);

        } catch (SQLiteAccessPermException e) {
            e.printStackTrace();
        }
    }
    public void show_prosueta () {

        SQLiteDatabase mydatabase=null;
        Integer n=0;
        moviesList=(GridView)findViewById(R.id.listKathgp);
        //recyclerView=(RecyclerView) findViewById(R.id.grid2);
        //   List<String> values=new ArrayList<>();
        values=new ArrayList<String>();

        try{
            mydatabase = openOrCreateDatabase("eidh",MODE_PRIVATE,null);
            Cursor cursor2 = mydatabase.rawQuery("SELECT ONO,ID FROM XAR1 ", null);
            String kat="";
            String syn="";
            if (cursor2.moveToFirst()) {
                do {
                    values.add( Integer.toString(cursor2.getInt(1) ));
                    values.add(cursor2.getString(0));

                } while (cursor2.moveToNext());
            }
            mydatabase.close();
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);
            moviesList.setAdapter(arrayAdapter);








            // TrapeziaList.setAdapter(arrayAdapter);

            //final MyAdapter adapter = new MyAdapter();
            //  rv.setAdapter(adapter);
            //   GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            //  rv.setLayoutManager(mLayoutManager);

// set up the RecyclerView
            //   RecyclerView recyclerView = findViewById(R.id.rvAnimals);
            //    TrapeziaList.setLayoutManager(new LinearLayoutManager(this));
            //   adapter = new MyRecyclerViewAdapter(this, animalNames);
            //   adapter.setClickListener(this);
            //   recyclerView.setAdapter(adapter);

        } catch (SQLiteAccessPermException e) {
            e.printStackTrace();
        }
    }

    public class EIDHadapter extends BaseAdapter {  // implements OnClickListener
        private Context context;

        private List<EIDOS> listOfEIDOS;
        private Integer nPointer;

        public EIDHadapter(Context context, List<EIDOS> listOfEIDOS) {
            this.context = context;
            this.listOfEIDOS = listOfEIDOS;
        }

        public int getCount() {
            return listOfEIDOS.size();
        }



        public Object getItem(int position) {

            return listOfEIDOS.get(position);
            // return listPhonebook.get(getCount() - position - 1);
        }

        public long getItemId(int position) {

            return position;  // κανονικη : το τελευταιο κατω
            //  return  ( getCount() - position - 1);
        }

        //  @Override
        //  public Object getItem(int position) {
        //      return getCount() - position - 1;
        //  }




        public View getView(int position, View convertView, ViewGroup viewGroup) {
            EIDOS entry = listOfEIDOS.get(position);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.eidos, null);
            }
            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            tvName.setText(entry.getName());



           TextView tvPhone = (TextView) convertView.findViewById(R.id.tvID);
            tvPhone.setText(entry.getID().toString());


            TextView tvMail = (TextView) convertView.findViewById(R.id.tvTimh);
            tvMail.setText(entry.getTimh().toString());

            TextView tvProsu = (TextView) convertView.findViewById(R.id.tvProsu);
            tvProsu.setText(entry.getProsu());

            TextView tvTimhp = (TextView) convertView.findViewById(R.id.tvTimhp);
            tvTimhp.setText(entry.getTimhp().toString());

            TextView tvKathg = (TextView) convertView.findViewById(R.id.tvSxolia);
            tvKathg.setText(entry.getKathg());
            tvKathg.setTextColor(Color.GREEN);

          //  nPointer=entry.getPointer();

            // Set the onClick Listener on this button
            Button btnRemove = (Button) convertView.findViewById(R.id.btnRemove);
            if (entry.getStatus()>0){  // μολις παραγγειλε
                tvName.setTextColor(Color.GREEN);
                //  tvName.setEnabled(true);
                //  tvName.setText(entry.getPointer()+entry.getName());

            } else{ //παλια
                tvName.setTextColor(Color.BLACK );
                //  tvName.setEnabled(false);
                btnRemove.setWidth(70);
                //debug  tvName.setText(entry.getStatus()+entry.getName());
            }

            if (entry.getName().substring(0,1).equals("*")){
                tvName.setTextColor(Color.GREEN);
            }






            btnRemove.setFocusableInTouchMode(false);
            btnRemove.setFocusable(false);




            //To lazy to implement interface
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Phonebook entry = (Phonebook) v.getTag();
                    // TextView tvMail = (TextView) order.findViewById(R.id.tvTimh);

                    //              Button mer=super.findViewById(R.id.merikiB);

                    Toast.makeText(context,nPointer.toString(),Toast.LENGTH_SHORT).show();
                    //  listPhonebook.remove(entry);
                    // listPhonebook.remove(view.getId());

                    //  notifyDataSetChanged();

                }
            });





            //debug   btnRemove.setOnClickListener(this);


            // Set the entry, so that you can capture which item was clicked and
            // then remove it
            // As an alternative, you can use the id/position of the item to capture
            // the item
            // that was clicked.
            btnRemove.setTag(entry);

            // btnRemove.setId(position);


            return convertView;
        }

        //  @Override   // θα χρειαστεί στον ορισμό της κλάσης ... implements OnClickListener {...
        // και στο getView()       btnRemove.setOnClickListener(this);
        //public void onClick(View view) {
        //   Phonebook entry = (Phonebook) view.getTag();
        //   listPhonebook.remove(entry);
        //    // listPhonebook.remove(view.getId());
        //    notifyDataSetChanged();
        // }

        private void showDialog(Phonebook entry) {
            // Create and show your dialog
            // Depending on the Dialogs button clicks delete it or do nothing
        }

    }



}
