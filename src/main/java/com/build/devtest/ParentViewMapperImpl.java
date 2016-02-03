package com.build.devtest;

import java.util.*;


//ParentViewMapperImpl class implementation
public class ParentViewMapperImpl implements ParentViewMapper
{
    public List<ParentView> mapRowsToViews(List<ParentRow> parentRows, List<ChildRow> childRows)
    {
        List<ParentView> outList = new ArrayList<ParentView>(); // final results list
        List<String> IDList = new ArrayList<String>();

        // traversing list of parentRows and adding to final results list
        for(ParentRow par : parentRows)
        {
            ParentView pV = new ParentView(par.getFirstName(),
                                par.getLastName(),
                                par.getAge(),
                                par.getParentId(),
                                new ArrayList<ChildView>());

            IDList.add(par.getParentId().toLowerCase());
            outList.add(pV);
        }

        /* traversing list of childRows and adding to appropriate parent
          within final results list (if exists) */
        for(ChildRow chd : childRows)
        {
            if(IDList.contains(chd.getParentId().toLowerCase()))
            {
                int loc = IDList.indexOf(chd.getParentId().toLowerCase());

                ChildView cV = new ChildView(chd.getFirstName(),
                                    chd.getLastName(),
                                    chd.getAge(),
                                    chd.getChildId(),
                                    outList.get(loc));

                outList.get(loc).getChildViews().add(cV);
            }

            else
            {
                System.out.println("Insufficient data!\n");
                return null;
            }
        }

        return outList;
    }
}
