package com.intsoftdev.yvdemoapp.ui.usersummary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.intsoftdev.domain.UserDetails
import com.intsoftdev.yvdemoapp.databinding.UserSummaryItemBinding

class UsersSummaryAdapter(
    private val users: List<UserDetails>,
    private val listener: (UserDetails) -> Unit
) : RecyclerView.Adapter<UsersSummaryAdapter.UserViewHolder>() {

    inner class UserViewHolder(private val itemBinding: UserSummaryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(userDetails: UserDetails) {
            itemBinding.userName.text = userDetails.displayName
            itemBinding.userImage.load(userDetails.picUrl)
            itemView.setOnClickListener {
                listener.invoke(userDetails)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemBinding =
            UserSummaryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(users[position])

    override fun getItemCount(): Int = users.size
}