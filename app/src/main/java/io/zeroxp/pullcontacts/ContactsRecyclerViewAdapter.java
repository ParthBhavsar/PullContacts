package io.zeroxp.pullcontacts;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by parthbhavsar on 2018-03-29.
 */

public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder>{

    public Context context;
    public ArrayList<Contact> contactList;
    FragmentManager fragmentManager;

    public ContactsRecyclerViewAdapter(Context context, FragmentManager fm)
    {
        this.contactList = new ArrayList<>();
        this.context = context;
        this.fragmentManager = fm;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView textViewPhoneNumber, textViewContactName;

        public Button btnContactEmblem;

        public RelativeLayout viewBackgroundSwipeRight, viewBackgroundSwipeLeft, viewForeground;


        public ViewHolder(View view) {
            super(view);

            textViewContactName = itemView.findViewById(R.id.textView_contactName);
            textViewPhoneNumber = itemView.findViewById(R.id.textView_contactNumber);
            btnContactEmblem = itemView.findViewById(R.id.contactEmblem);

            viewBackgroundSwipeRight = itemView.findViewById(R.id.view_backgroundSwipeRight);
            viewBackgroundSwipeLeft = itemView.findViewById(R.id.view_backgroundSwipeLeft);

            viewForeground = itemView.findViewById(R.id.view_foreground);

        }
    }

    @Override
    public ContactsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.e("AxelRecyclerViewAdapter", "Running onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list, parent, false);

        ContactsRecyclerViewAdapter.ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(ContactsRecyclerViewAdapter.ViewHolder holder, int position) {

        Contact contact = contactList.get(position);

        String firstCharacter = contact.getName().charAt(0) + "";


//        Random rnd = new Random();
//        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));

//        Drawable drawable = context.getDrawable(R.drawable.ic_circle);
//        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
//        holder.btnContactEmblem.setBackground(drawable);
        holder.btnContactEmblem.setText(firstCharacter.toUpperCase());
        holder.textViewContactName.setText(contact.getName());
        holder.textViewPhoneNumber.setText(contact.getNumber());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    public void insertNewContact(Contact contact)
    {

        contactList.add(contact);
        notifyDataSetChanged();
    }


    public void removeItem(int position) {
        contactList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void editContactItem(int position) {
        Toast.makeText(context, "Edit item", Toast.LENGTH_SHORT).show();
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()

        initDialog(position);


    }



    private void initDialog(final int position){

        final Contact contact = new Contact();


        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = layoutInflater.inflate(R.layout.edit_contact, null);
//        builder.setTitle("CAN ID : " + sensorPayload.getCanBusBoxId());


        final EditText editTextName = (EditText)view.findViewById(R.id.editText_ContactName);
        final EditText editTextNumber = (EditText)view.findViewById(R.id.editText_ContactNumber);

        final TextView textViewInvalidName = (TextView)view.findViewById(R.id.textView_InvalidName);
        final TextView textViewInvalidNumber = (TextView)view.findViewById(R.id.textView_InvalidNumber);

        final Button btnOk = (Button)view.findViewById(R.id.btnOk);
        final Button btnCancel = (Button)view.findViewById(R.id.btnCancel);


        editTextName.setText(contactList.get(position).getName());
        editTextNumber.setText(contactList.get(position).getNumber());


        builder.setView(view);

        final Dialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                notifyDataSetChanged();
            }
        });


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().toString().trim().equals("")) {
                    textViewInvalidName.setVisibility(View.VISIBLE);
                }

                if (editTextNumber.getText().toString().trim().equals(""))
                {
                    textViewInvalidNumber.setVisibility(View.VISIBLE);
                }

                if (!editTextName.getText().toString().trim().equals("") && !editTextNumber.getText().toString().trim().equals("")) {
                    contact.setName(editTextName.getText().toString());
                    contact.setNumber(editTextNumber.getText().toString());

                    contactList.set(position, contact);

                    dialog.dismiss();

                    notifyDataSetChanged();
                }
            }
        });


    }

}