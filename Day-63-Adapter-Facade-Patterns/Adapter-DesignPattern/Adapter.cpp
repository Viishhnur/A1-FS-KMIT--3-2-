#include <iostream>
#include <string.h>
using namespace std;

#define interface struct

interface MediaPlayer 
{
  virtual void play(char * audioType, char * fileName) = 0;
};

interface AdvancedMediaPlayer 
{	
  virtual void playVlc(char * fileName) = 0;
  virtual void playMp4(char * fileName) = 0;
};

class VlcPlayer : public AdvancedMediaPlayer 
{
  public:
    void playVlc(char * fileName) 
    {
      cout << "Playing vlc file. Name: " << fileName << endl;
    }
    void playMp4(char * fileName) 
    {
      //do nothing
    }
};

class Mp4Player : public AdvancedMediaPlayer
{
   public:
    void playVlc(char * fileName) 
    {
      //do nothing
    }
    void playMp4(char * fileName) 
    {
      cout << "Playing mp4 file. Name: " << fileName << endl;	
    }
};

class MediaAdapter : public MediaPlayer 
{
  private:
    AdvancedMediaPlayer *advancedMusicPlayer;
  public:
    MediaAdapter(char * audioType)
    {
      if(strcasecmp(audioType, "vlc") == 0)
      {
        advancedMusicPlayer = new VlcPlayer();		         
      } 
      else if(strcasecmp(audioType, "mp4") == 0)
      {
        advancedMusicPlayer = new Mp4Player();
      }	
    }

    void play(char * audioType, char * fileName)
    {
      if(strcasecmp(audioType, "vlc") == 0)
      {
        advancedMusicPlayer->playVlc(fileName);
      }
      else if(strcasecmp(audioType, "mp4") == 0)
      {
         advancedMusicPlayer->playMp4(fileName);
      }
    }
};

class AudioPlayer : public MediaPlayer 
{
   MediaAdapter *mediaAdapter; 
   public:     
    void play(char * audioType, char * fileName) 
    {		
      //inbuilt support to play mp3 music files
      if(strcasecmp(audioType, "mp3") == 0)
      {
         cout << "Playing mp3 file. Name: "  << fileName << endl;			
      }       
      //mediaAdapter is providing support to play other file formats
      else if(strcasecmp(audioType, "vlc") == 0 || strcasecmp(audioType, "mp4") == 0)
      {
         mediaAdapter = new MediaAdapter(audioType);
         mediaAdapter->play(audioType, fileName);
      }      
      else{
         cout << "Invalid media. " << audioType << " format not supported" << endl;
    }
  }   
};

int  main() {
  MediaPlayer *audioPlayer = new AudioPlayer();

  audioPlayer->play("mp3", "beyond the horizon.mp3");
  audioPlayer->play("mp4", "homealone.mp4");
  audioPlayer->play("vlc", "far far away.vlc");
  audioPlayer->play("avi", "test.avi");
  return 0;
}