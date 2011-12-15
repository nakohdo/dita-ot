/*
 * This file is part of the DITA Open Toolkit project hosted on
 * Sourceforge.net. See the accompanying license.txt file for
 * applicable licenses.
 */

/*
 * (c) Copyright IBM Corp. 2010 All Rights Reserved.
 */
package org.dita.dost.reader;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import org.dita.dost.TestUtils;
import org.dita.dost.module.Content;

/**
 * @author william
 *
 */
public class TestGenListModuleReader {

    public static GenListModuleReader reader;
    private static File baseDir = new File("test-stub", "DITA-OT1.5");

    private static File inputDir = new File("keyrefs" + File.separator + "maps_parallel_to_topics" + File.separator + "maps");
    private static File rootFile = new File(inputDir, "root-map-01.ditamap");

    @BeforeClass
    public static void setUp() throws Exception{
        //parser = new ConrefPushParser();
        String ditaDir = "";
        //get absolute path
        ditaDir = new File(baseDir, "").getAbsolutePath();

        final boolean validate = false;
        reader = new GenListModuleReader();
        reader.setLogger(new TestUtils.TestLogger());
        reader.initXMLReader(ditaDir, validate, new File(baseDir, rootFile.getPath()).getCanonicalPath(), true);
    }

    @Test
    public void testParse() throws Exception{
        //String inputDir = baseDir + "/maps";
        //inputDir = baseDir;
        //String inputMap = inputDir + "/root-map-01.ditamap";

        reader.parse(new File(baseDir, rootFile.getPath()));
        reader.getContent();
        final Set<String> conref = reader.getConrefTargets();
        final Set<String> chunk = reader.getChunkTopicSet();
        final Map<String, String> copytoMap = reader.getCopytoMap();
        final Set<String> hrefTargets = reader.getHrefTargets();
        final Set<String> hrefTopic =reader.getHrefTopicSet();
        final Set<String> copytoSet = reader.getIgnoredCopytoSourceSet();
        final Map<String, String> keyDMap = reader.getKeysDMap();
        final Set<String> nonConref = reader.getNonConrefCopytoTargets();
        final Set<String> nonCopyTo = reader.getNonCopytoResult();
        final Set<String> outDita = reader.getOutDitaFilesSet();
        final Set<String> outFiles = reader.getOutFilesSet();
        final Set<String> resourceOnlySet = reader.getResourceOnlySet();
        final Set<String> subsidiaryTargets = reader.getSubsidiaryTargets();

        assertEquals(0, conref.size());

        assertEquals(0, chunk.size());

        assertEquals(0, copytoMap.size());

        assertTrue(hrefTargets.contains(".." + File.separator + "topics" + File.separator + "xreffin-topic-1.xml"));
        assertTrue(hrefTargets.contains(".." + File.separator + "topics" + File.separator + "target-topic-c.xml"));
        assertTrue(hrefTargets.contains(".." + File.separator + "topics" + File.separator + "target-topic-a.xml"));

        assertTrue(hrefTopic.contains(".." + File.separator + "topics" + File.separator + "xreffin-topic-1.xml"));
        assertTrue(hrefTopic.contains(".." + File.separator + "topics" + File.separator + "target-topic-c.xml"));
        assertTrue(hrefTopic.contains(".." + File.separator + "topics" + File.separator + "target-topic-a.xml"));

        assertEquals(0, copytoSet.size());

        assertEquals(".." + File.separator + "topics" + File.separator + "target-topic-c.xml",keyDMap.get("target_topic_2"));
        assertEquals(".." + File.separator + "topics" + File.separator + "target-topic-a.xml",keyDMap.get("target_topic_1"));

        assertTrue(nonConref.contains(".." + File.separator + "topics" + File.separator + "xreffin-topic-1.xml"));
        assertTrue(nonConref.contains(".." + File.separator + "topics" + File.separator + "target-topic-c.xml"));
        assertTrue(nonConref.contains(".." + File.separator + "topics" + File.separator + "target-topic-a.xml"));

        assertTrue(nonCopyTo.contains(".." + File.separator + "topics" + File.separator + "xreffin-topic-1.xml"));
        assertTrue(nonCopyTo.contains(".." + File.separator + "topics" + File.separator + "target-topic-c.xml"));
        assertTrue(nonCopyTo.contains(".." + File.separator + "topics" + File.separator + "target-topic-a.xml"));

        assertTrue(outDita.contains(".." + File.separator + "topics" + File.separator + "xreffin-topic-1.xml"));
        assertTrue(outDita.contains(".." + File.separator + "topics" + File.separator + "target-topic-c.xml"));
        assertTrue(outDita.contains(".." + File.separator + "topics" + File.separator + "target-topic-a.xml"));

        assertTrue(outFiles.contains(".." + File.separator + "topics" + File.separator + "xreffin-topic-1.xml"));
        assertTrue(outFiles.contains(".." + File.separator + "topics" + File.separator + "target-topic-c.xml"));
        assertTrue(outFiles.contains(".." + File.separator + "topics" + File.separator + "target-topic-a.xml"));

        assertEquals(0, resourceOnlySet.size());

        assertEquals(0, subsidiaryTargets.size());
    }
}
