package com.example.nycschoolsapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nycschoolsapp.model.SchoolInfoResponse
import com.example.nycschoolsapp.utils.ViewType
import com.example.nycschoolsapp.databinding.LetterHolderBinding
import com.example.nycschoolsapp.databinding.SchoolInformationBinding
import com.example.nycschoolsapp.databinding.SchoolsDataHolderBinding

/**
 * [Class] - School Information Adapter for Nested Recycler View
 */
class SchoolInformationAdapter(
    private val schoolsInfo: MutableList<ViewType> = mutableListOf(),
    private val onClickedSchool: (String) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    fun updateSchools(newSchools: List<SchoolInfoResponse>){
        var tempChar = '+'
        newSchools.sortedBy { it.schoolName }.forEach { school ->
            val firstLetter = school.schoolName?.first() ?: '+'
            if(firstLetter != tempChar) {
                schoolsInfo.add(ViewType.LETTER(firstLetter.toString()))
                tempChar = firstLetter
            }
            schoolsInfo.add(ViewType.SCHOOLS_DATA(school))
        }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == 0){
            LetterViewHolder(
                LetterHolderBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false
                )
            )
        } else{
            SchoolViewHolder(
                SchoolInformationBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false
                )
            )
        }
    }

    override fun getItemCount() = schoolsInfo.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = schoolsInfo[position]){
            is ViewType.LETTER -> {
                (holder as LetterViewHolder).letterDataBinding(item.letter)
            }
            is ViewType.SCHOOLS_DATA -> {
                (holder as SchoolViewHolder).schoolBinding(item.schoolsData, onClickedSchool)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(schoolsInfo[position]){
            is ViewType.LETTER -> 0
            is ViewType.SCHOOLS_DATA -> 1
        }
    }

}

class LetterViewHolder(
    private val binding: LetterHolderBinding
): RecyclerView.ViewHolder(binding.root){
    fun letterDataBinding(letter: String){
        binding.tvLetter.text = letter
    }
}