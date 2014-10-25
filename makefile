sources  = ./src/

default: 
	$(MAKE) --directory=$(sources)

.PHONY: clean
clean:
	rm -f class/*.class


