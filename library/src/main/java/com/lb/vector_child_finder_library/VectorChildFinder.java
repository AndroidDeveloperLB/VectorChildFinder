package com.lb.vector_child_finder_library;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by ${Deven} on 2/1/18.
 * PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
 * myImageView.setColorFilter(porterDuffColorFilter);
 */

public class VectorChildFinder {

    public interface TraverseListener {
        /**
         * occurs on each traversing over anything in the tree of VectorDrawable. First is always the root.
         *
         * @return true if you wish to continue traversing
         */
        boolean OnTraverse(@NonNull Object node);
    }

    @NonNull
    public final VectorDrawableCompat vectorDrawable;

    /**
     * @param context   Your Activity Context
     * @param vectorRes Path of your vector drawable resource
     * @param imageView ImaveView that are showing vector drawable
     */
    public VectorChildFinder(@NonNull final Context context, final int vectorRes, @Nullable final ImageView imageView) {
        vectorDrawable = VectorDrawableCompat.create(context.getResources(),
                vectorRes, null);
        vectorDrawable.setAllowCaching(false);
        if (imageView != null)
            imageView.setImageDrawable(vectorDrawable);
//        imageView.setOnTouchListener((v, event) -> {
//            if (event.getAction() == MotionEvent.ACTION_UP) {
//                v.performClick();
//            }
//            vectorDrawable.getTouchedPath(event);
//            return true;
//        });
    }

    @NonNull
    public HashMap<String, ArrayList<VectorDrawableCompat.VFullPath>> getPathNameToVFullPathMap() {
        final HashMap<String, ArrayList<VectorDrawableCompat.VFullPath>> result = new HashMap<>();
        traverseAllTargets(node -> {
            if (node instanceof VectorDrawableCompat.VFullPath) {
                final String pathName = ((VectorDrawableCompat.VFullPath) node).getPathName();
                if (pathName != null) {
                    ArrayList<VectorDrawableCompat.VFullPath> vFullPaths = result.get(pathName);
                    if (vFullPaths == null) {
                        vFullPaths = new ArrayList<>();
                        result.put(pathName, vFullPaths);
                    }
                    vFullPaths.add((VectorDrawableCompat.VFullPath) node);
                }
            }
            return true;
        });
        return result;
    }

    /**
     * @param pathName Path name that you gave in vector drawable file
     * @return A Object type of VectorDrawableCompat.VFullPath
     */
    @Nullable
    public VectorDrawableCompat.VFullPath findPathByName(@NonNull final String pathName) {
        return (VectorDrawableCompat.VFullPath)
                vectorDrawable.getTargetByName(pathName);
    }

    /**
     * @param groupName Group name that you gave in vector drawable file
     * @return A Object type of VectorDrawableCompat.VGroup
     */
    @Nullable
    public VectorDrawableCompat.VGroup findGroupByName(@NonNull final String groupName) {
        return (VectorDrawableCompat.VGroup)
                vectorDrawable.getTargetByName(groupName);
    }

    @NonNull
    public VectorDrawableCompat.VGroup getRootGroup() {
        return vectorDrawable.mVectorState.mVPathRenderer.mRootGroup;
    }

    /**
     * traverses all nodes of the VectorDrawable. Allows to halt if needed.
     */
    public void traverseAllTargets(@NonNull final TraverseListener traverseListener) {
        final VectorDrawableCompat.VGroup rootGroup = vectorDrawable.mVectorState.mVPathRenderer.mRootGroup;
        if (!traverseListener.OnTraverse(rootGroup))
            return;
        final LinkedList<VectorDrawableCompat.VGroup> remainingGroupsToTraverse = new LinkedList<>();
        remainingGroupsToTraverse.add(rootGroup);
        while (true) {
            final VectorDrawableCompat.VGroup vGroup = remainingGroupsToTraverse.pollFirst();
            if (vGroup == null)
                break;
            for (final Object childNode : vGroup.mChildren) {
                if (!traverseListener.OnTraverse(childNode))
                    return;
                if (childNode instanceof VectorDrawableCompat.VGroup)
                    remainingGroupsToTraverse.add((VectorDrawableCompat.VGroup) childNode);
            }
        }
    }
}
