package com.alokomkar.stocklist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.alokomkar.stocklist.databinding.DialogUserStockSummaryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserStockSummaryDialog: BottomSheetDialogFragment() {

    private val viewModel: UserStocksViewModel by activityViewModels()
    private lateinit var dialogUserStockSummaryBinding: DialogUserStockSummaryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialogUserStockSummaryBinding = DialogUserStockSummaryBinding.inflate(inflater, container, false)
        return dialogUserStockSummaryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogUserStockSummaryBinding.apply {

        }
    }

    companion object {
        const val TAG = "UserStockSummaryDialog"
    }

}