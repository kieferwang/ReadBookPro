package com.krisitine.readbookpro.ui;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.krisitine.readbookpro.KeyboardUtils;
import com.krisitine.readbookpro.R;
import com.krisitine.readbookpro.adapter.SearchAdapter;
import com.krisitine.readbookpro.bean.Book;
import com.krisitine.readbookpro.db.DB;
import com.krisitine.readbookpro.net.BaseAsyncHttp;
import com.krisitine.readbookpro.net.HttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tyq on 2015/9/5.
 */
public class SearchAcitvity extends Activity {
    private ListView listView;
    private EditText et_search;
    private RelativeLayout btn_search;
    private List<Book> mBooks = new ArrayList<Book>();
    private SearchAdapter mAdapter;
    private DB db;
    private SQLiteDatabase dbWrite;
    private AddDialog addDialog;
    private int mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        et_search = (EditText) findViewById(R.id.et_search_content);
        btn_search = (RelativeLayout) findViewById(R.id.rl_search_btn);
        listView = (ListView) findViewById(R.id.lv_search);
        db = new DB(SearchAcitvity.this);
        dbWrite = db.getWritableDatabase();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mItem = i;
                addDialog = new AddDialog(SearchAcitvity.this, R.style.dialog_style);
                ImageButton btn_add = (ImageButton) addDialog.getPosBtn();
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentValues cv = new ContentValues();
                        cv.clear();
                        cv.put("title", mBooks.get(mItem).getTitle());
                        cv.put("page", mBooks.get(mItem).getPage());
                        dbWrite.insert("book", null, cv);
                        addDialog.dismiss();
                        Toast.makeText(SearchAcitvity.this, "已添加", Toast.LENGTH_SHORT).show();
                    }
                });
                addDialog.show();
            }
        });


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeyboardUtils.closeKeyBoard(SearchAcitvity.this);
                getRequestData(et_search.getText().toString());
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals("")) {
                    btn_search.setVisibility(View.GONE);
                } else {
                    btn_search.setVisibility(View.VISIBLE);
                }
            }
        });

        mAdapter = new SearchAdapter(mBooks, this);
        listView.setAdapter(mAdapter);


    }

    public void getRequestData(String str) {
        RequestParams params = new RequestParams();
        params.put("q", str.trim());
        BaseAsyncHttp.getReq("/v2/book/search", params, new HttpResponseHandler() {
            @Override
            public void jsonSuccess(JSONObject resp) {
                mBooks.clear();

                JSONArray jsonbooks = resp.optJSONArray("books");
                for (int i = 0; i < jsonbooks.length(); i++) {

                    try {
                        Book mBook = new Book();
                        mBook.setTitle(jsonbooks.optJSONObject(i).optString("title"));
                        String author = "";
                        for (int j = 0; j < jsonbooks.optJSONObject(i).optJSONArray("author").length(); j++) {
                            author = author + " " + jsonbooks.optJSONObject(i).optJSONArray("author").optString(j);
                        }
                        mBook.setAuthor(author);
                        mBook.setBitmap(jsonbooks.optJSONObject(i).getString("image"));
                        mBook.setPage(jsonbooks.optJSONObject(i).optString("pages"));
                        mBooks.add(mBook);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                updateToView();
            }

            @Override
            public void jsonFail(JSONObject resp) {
                Toast.makeText(SearchAcitvity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateToView() {
        mAdapter.setData(mBooks);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActionBar().setHomeButtonEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
