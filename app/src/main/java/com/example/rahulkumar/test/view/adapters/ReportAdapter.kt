package com.example.rahulkumar.test.view.adapters

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.example.rahulkumar.test.R
import com.example.rahulkumar.test.database.Report
import com.example.rahulkumar.test.view.activities.main.IMainView

/**
 * Created by rahulkumar on 22/04/18.
 */
class ReportAdapter(iMainView: IMainView) : RecyclerView.Adapter<ReportAdapter.MyViewHolder>(){

    var mList :List<Report> = arrayListOf()
    val mMainView=iMainView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.report_row_item,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mMedicineName.text=mList.get(position).name
        holder.mTvDosage.text=mList.get(position).dosage
        holder.mTvDateTime.text=mList.get(position).date
        holder.mTvStatus.text=mList.get(position).status
        holder.mTvEdit.tag=position

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        @BindView(R.id.tvName)
        lateinit var mMedicineName:AppCompatTextView

        @BindView(R.id.tvDosage)
        lateinit var mTvDosage:AppCompatTextView

        @BindView(R.id.tvDateTime)
        lateinit var mTvDateTime : AppCompatTextView

        @BindView(R.id.tvEdit)
        lateinit var mTvEdit :AppCompatTextView

        @BindView(R.id.tvStatus)
        lateinit var mTvStatus :AppCompatTextView

        init {

            ButterKnife.bind(this,itemView)
            mTvEdit.setOnClickListener {
                it->
                val position=it.tag as Int
                mMainView.editRow(mList.get(position).id,position)
            }
        }
    }

    fun setList(list: List<Report>){
        mList=list
        notifyDataSetChanged()
    }
}