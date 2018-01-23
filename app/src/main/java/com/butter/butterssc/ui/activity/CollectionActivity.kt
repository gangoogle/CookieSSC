package com.butter.butterssc.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.butter.butterssc.R
import com.butter.butterssc.adapter.RCCollectionAdapter
import com.butter.butterssc.adapter.RCLottertAdapter
import com.butter.butterssc.model.response.CaiPiaoResponse
import com.butter.butterssc.utils.ComUtils
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.activity_lottery_detail.*

class CollectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        rc_view.layoutManager = LinearLayoutManager(this)
        rc_view.adapter = RCCollectionAdapter(this, ComUtils.getCollections(this))
        iv_back_a.setOnClickListener{finish()}
    }
}
