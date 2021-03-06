package sound;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class VoiceTest {
    /*
     * Testing Strategy:
     * 
     * To test equals(), we created instances of each class, each varying by the others slightly. We created instances that reflected different combinations of differences. 
     * Tested to make sure equals() was reflexive and that two structurally equal instances were equals().
     * 
     * To test toString(), we created instances of each class, each varying by the others slightly. We created instances that reflected different combinations of differences. 
     * Tested to make sure instances returned the correct string and that structurally equivalent instances returned the same string.
     * 
     * To test hashCode(), we tested to be sure that structurally equivalent instances returned the same hash code and that hashCode() was reflexive.
     * 
     * To test clone(), we tested to be sure that a clone of an instance was equals() to its parent, returned equivalent strings, and returned equivalent hash codes.
     * 
     * To test getTicksPerVoice(), we created voices that had differing names and maxNotes. We tested to make sure that getTicksPerVoice was independent to voice name and that voices with the same maxNote returned the same value
     * 
     * To test addMusicalElement(), we created a voice and added to it. After each addition, we checked to make sure that it contained all the necessary notes, both the ones it started with, and the ones we added. This also tests getMuscicalElements().
     */

    @Test
    public void testVoiceEquals() {
        Voice voice1 = new Voice("Bass", 1.0);
        Voice voice2 = new Voice("Bass", 1.0);
        Voice voice3 = new Voice("Soprano", 1.0);
        Voice voice4 = new Voice("Bass", 1.5);
        Voice voice5 = new Voice("Soprano", 2.0);

        assertEquals(true, voice1.equals(voice2));
        assertEquals(true, voice1.equals(voice1));
        assertEquals(false, voice1.equals(voice3));
        assertEquals(false, voice1.equals(voice4));
        assertEquals(false, voice1.equals(voice5));
    }

    @Test
    public void testVoiceHashCode(){
        Voice voice1 = new Voice("Bass", 1.0);
        Voice voice2 = new Voice("Bass", 1.0);

        assertEquals(true, voice1.hashCode() == voice1.hashCode());
        assertEquals(true, voice1.hashCode() == voice2.hashCode());

    }

    @Test
    public void testVoiceToString() {
        Voice voice1 = new Voice("Bass", 1.0);
        Voice voice2 = new Voice("Bass", 1.0);
        Voice voice3 = new Voice("Soprano", 1.0);
        Voice voice4 = new Voice("Bass", 1.5);
        Voice voice5 = new Voice("Soprano", 2.0);

        assertEquals(true, voice1.toString().equals(voice2.toString()));
        assertEquals(true, voice1.toString().equals(voice1.toString()));
        assertEquals(false, voice1.toString().equals(voice3.toString()));
        assertEquals(true, voice1.toString().equals(voice4.toString()));
        assertEquals(false, voice1.toString().equals(voice5.toString()));
    }

    @Test
    public void testVoiceTestGetNotesPerVoice() {
        Voice voice1 = new Voice("Bass", 1.0);
        Voice voice2 = new Voice("Bass", 1.0);
        Voice voice3 = new Voice("Soprano", 1.0);
        Voice voice4 = new Voice("Bass", 1.5);
        Voice voice5 = new Voice("Soprano", 2.0);

        assertEquals(true, voice1.getNotesPerVoice() == 1.0);
        assertEquals(false, voice1.getNotesPerVoice() == 1.5);
        assertEquals(true, voice1.getNotesPerVoice() == voice2.getNotesPerVoice());
        assertEquals(true, voice1.getNotesPerVoice() == voice3.getNotesPerVoice());
        assertEquals(false, voice1.getNotesPerVoice() == voice4.getNotesPerVoice());
        assertEquals(false, voice1.getNotesPerVoice() == voice5.getNotesPerVoice());
    }

    @Test
    public void testVoiceClone() {
        Voice voice1 = new Voice("Bass", 1.0);
        Voice voice2 = new Voice("Bass", 1.0);
        Voice voice3 = new Voice("Soprano", 1.0);
        Voice voice4 = new Voice("Bass", 1.5);
        Voice voice5 = new Voice("Soprano", 2.0);
        Voice voice6 = voice1.clone();

        assertEquals(true, voice6.equals(voice2));
        assertEquals(true, voice6.equals(voice1));
        assertEquals(false, voice6.equals(voice3));
        assertEquals(false, voice6.equals(voice4));
        assertEquals(false, voice6.equals(voice5));
        assertEquals(true, voice1.equals(voice6));
    }


    public void testVoiceAddMusicalElementAndGetMusicalElements() {
        Voice voice1 = new Voice("Bass", 1.0);

        List<Note> notesToCheck = new LinkedList<Note>();

        List<Pitch> pitches1 = new LinkedList<Pitch>();
        pitches1.add(new Pitch('C'));
        pitches1.add(new Pitch('E'));
        pitches1.add(new Pitch('G'));

        List<Pitch> pitches2 = new LinkedList<Pitch>();
        pitches2.add(new Pitch('C'));
        pitches2.add(new Pitch('F'));
        pitches2.add(new Pitch('G'));

        Note note1 = new Note(new Pitch('C'), new Fraction(1, 4));
        Note note2 = new Note(new Pitch('E'), new Fraction(1, 2));

        try {
            voice1.addMusicalElement(note1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        notesToCheck.add(note1);
        assertEquals(true, voice1.getMusicalElements().equals(notesToCheck));

        try {
            voice1.addMusicalElement(note1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        notesToCheck.add(note1);
        assertEquals(true, voice1.getMusicalElements().equals(notesToCheck));

        try {
            voice1.addMusicalElement(note2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        notesToCheck.add(note2);
        assertEquals(true, voice1.getMusicalElements().equals(notesToCheck));
    }

    @Test
    public void testVoiceGetTicksPerWholeNote() {
        Voice voice1 = new Voice("Bass", 1.0);
        Voice voice2 = new Voice("Soprano", 1.0);
        Voice voice3 = new Voice("Tenor", 1.0);
        Voice voice4 = new Voice("Bob", 1.0);


        Note note1 = new Note(new Pitch('C'), new Fraction(1, 4));
        Note note2 = new Note(new Pitch('E'), new Fraction(1, 2));
        Note note3 = new Note(new Pitch('C'), new Fraction(1,2));
        Note note4 = new Note(new Pitch('F'), new Fraction(1, 4));

        try {
            voice1.addMusicalElement(note1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            voice2.addMusicalElement(note2);
        } catch(Exception e){
            e.printStackTrace();
        }

        try {
            voice3.addMusicalElement(note3);
        } catch(Exception e){
            e.printStackTrace();
        }
        try {
            voice4.addMusicalElement(note4);
        } catch(Exception e){
            e.printStackTrace();
        }



        assertEquals(true, voice1.getTicksPerWholeNote() == voice4.getTicksPerWholeNote());
        assertEquals(true, voice2.getTicksPerWholeNote() == voice3.getTicksPerWholeNote());
        assertEquals(false, voice1.getTicksPerWholeNote() == voice2.getTicksPerWholeNote());
    }
}
