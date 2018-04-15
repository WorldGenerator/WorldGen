using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayControl : MonoBehaviour {

    Animator anim;
    public float rotSpeed;
	// Use this for initialization
	void Start () {
        anim = GetComponent<Animator>();
		
	}
	
	// Update is called once per frame
	void Update () {
        float horiz = Input.GetAxis("Horizontal");
        float vert = Input.GetAxis("Vertical");

        if(vert > 0.0f)
        {
            anim.SetBool("Moving", true);
        }
        else
        {
            anim.SetBool("Moving",false);
        }
        if (Input.GetButtonDown("Fire1"))
        {
            anim.SetBool("Attack1Trigger", true);
        }
        else
        {
            anim.SetBool("Attack1Trigger", false);
        }
        if(horiz != 0.0f)
        {
            transform.Rotate(new Vector3(0.0f, horiz * rotSpeed, 0.0f));
        }


		
	}
}
