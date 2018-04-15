using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;
using System.IO;

public class LoadImage : MonoBehaviour {

    public char myChar;
    private int size;
    public Transform prefab;
	// Use this for initialization
	void Start () {
        size = 25;
        ReadInfo();
		
	}

    void ReadInfo(){
        string path = "Assets/Resources/world.txt";
        StreamReader rd = new StreamReader(path);
        float z = (float) -1 * size + 0.5f;
        while(rd.Peek() >=0 && z < size){
            string row = rd.ReadLine();
            float x = (float) -1 * size + 0.5f;
            char[] crs = row.ToCharArray();
            int index = 0;
            while(index < crs.Length){
                if(crs[index] == myChar){
                    Instantiate(prefab, new Vector3(x,0.0f,z), Quaternion.identity);
                }
                index++;
                x++;
            }
            z++; 
    }
    }
}
