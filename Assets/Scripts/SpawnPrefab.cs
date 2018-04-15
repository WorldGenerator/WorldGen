using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnPrefab : MonoBehaviour {

    // Use this for initialization
    public int number;
    public Transform prefab;
    public float myRadius = 2.0f; //Radius of the sphere described round object
    public Vector3 changeVector;
    
    void Start()
    {
        Spawn(number);
    }

    void Spawn(int num)
    {
        Debug.Log("Spawning" + num);
        Vector3 randPos = Vector3.zero;
        int myCheck = 0; //count overlap colliders
        int attempts = 0;
        for (int i = 0; i < num; i++)
        {
            //@source answers.unity.com zharik86
            do
            {
                myCheck = 0;
                randPos = new Vector3(Random.Range(-25.0f, 25.0f), 0.0f, Random.Range(-25.0f, 25.0f));
                Collider[] hitColliders = Physics.OverlapSphere(randPos, myRadius);
                myCheck = hitColliders.Length;
                attempts++;
            } while (myCheck > 1 && attempts < 2 * num);
            if(attempts < 2 * num)
            {
                Transform newObj = (Transform)Instantiate(prefab, randPos, Quaternion.identity);
            }
        }
    }
    private int nextUpdate = 10;

    // Update is called once per frame
    void Update()
    {

        // If the next update is reached
        if (Time.time >= nextUpdate)
        {
            Debug.Log(Time.time + ">=" + nextUpdate);
            // Change the next update (current second+1)
            nextUpdate = Mathf.FloorToInt(Time.time) + 1;
            // Call your fonction
            UpdateEverySecond();
        }

    }
    // Update is called once per frame
    void UpdateEverySecond ()
    {
        int plants = GameObject.FindGameObjectsWithTag("Plant").Length;
        int rabbits = GameObject.FindGameObjectsWithTag("Rabbit").Length;
        int foxes = GameObject.FindGameObjectsWithTag("Fox").Length;
        int spawn = (int) Vector3.Dot(changeVector, new Vector3(plants, rabbits, foxes));
        Spawn(spawn);
        
    }
}
