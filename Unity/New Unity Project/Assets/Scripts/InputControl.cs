using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InputControl : MonoBehaviour {

    // Use this for initialization
    Animator anim;
    int attack = Animator.StringToHash("Attack1Trigger");

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
            anim.SetBool("Moving", false); 
        }
        if (Input.GetKeyDown(KeyCode.Space))
        {
            anim.SetTrigger(attack);
        }

		
	}
}
