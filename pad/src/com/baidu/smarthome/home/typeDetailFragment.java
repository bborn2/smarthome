
package com.baidu.smarthome.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A fragment representing a single type detail screen. This fragment is either
 * contained in a {@link typeListActivity} in two-pane mode (on tablets) or a
 * {@link typeDetailActivity} on handsets.
 */
public class typeDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Contents.DummyItem mItem;

    MsgAdapter mAdapter;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public typeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = Contents.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.msg_history, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            mAdapter = new MsgAdapter(this.getActivity().getApplicationContext());
            if ( Contents.getTestData(mItem.content) != null) {
                for (Talk t : Contents.getTestData(mItem.content)) {
                    mAdapter.updateData(t.from, t.content);
                }
            }
            ListView list = ((ListView) rootView.findViewById(R.id.list));
            list.setAdapter(mAdapter);
        }

        return rootView;
    }
}
