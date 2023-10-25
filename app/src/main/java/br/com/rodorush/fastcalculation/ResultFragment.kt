package br.com.rodorush.fastcalculation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rodorush.fastcalculation.Extras.ARG_POINTS
import br.com.rodorush.fastcalculation.Extras.EXTRA_SETTINGS
import br.com.rodorush.fastcalculation.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private lateinit var fragmentResultBinding: FragmentResultBinding
    private lateinit var settings: Settings
    private var points:Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            points = it.getFloat(ARG_POINTS, 0f)
            settings = it.getParcelable(EXTRA_SETTINGS) ?: Settings()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)

        "%.1f".format(points).also {
            fragmentResultBinding.scoreTv.text = it
        }

        val onClickListener = View.OnClickListener {
            (requireActivity() as? OnPlayGame)?.onPlayGame()
        }

        fragmentResultBinding.restartBt.setOnClickListener(onClickListener)

        return fragmentResultBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(points: Float, settings: Settings) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putFloat(ARG_POINTS, points)
                    putParcelable(EXTRA_SETTINGS, settings)
                }
            }
    }
}